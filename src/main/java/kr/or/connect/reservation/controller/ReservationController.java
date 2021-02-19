package kr.or.connect.reservation.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.service.MemberService;
import kr.or.connect.reservation.service.ReservationService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservationInfos")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final MemberService memberService;

    @ApiOperation(value = "예약 등록하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Exception")
    })
    @PostMapping
    public RegistrationResponse register(@RequestBody RegistrationRequest request) {
        return reservationService.reserve(request);
    }

    @ApiOperation(value = "로그인한 사용자의 주문 정보 구하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Exception")
    })
    @GetMapping
    public OrderResponse searchOrders(Principal principal) {
        String loginId = principal.getName();
        Member member = memberService.getMemberByEmail(loginId);
        List<Order> findOrders = reservationService.getOrdersByUser(member);
        return new OrderResponse(findOrders.size(), findOrders);
    }

    @ApiOperation(value = "로그인한 사용자의 예약 취소하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Exception")
    })
    @PutMapping
    public Map<String,String> cancelOrders(@RequestBody Map<String,Integer> request, Principal principal){
        String loginId = principal.getName();
        return reservationService.cancelOrders(request.get("id").longValue(),loginId);
    }

}
