package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dao.MemberDao;
import kr.or.connect.reservation.dao.ReservationDao;
import kr.or.connect.reservation.dao.ReservationPriceDao;
import kr.or.connect.reservation.dto.*;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationDao reservationDao;
    private final ReservationPriceDao reservationPriceDao;
    private final MemberDao memberDao;

    private final int NOT_CANCEL = 0;

    @Transactional
    public RegistrationResponse reserve(RegistrationRequest request) {
        Member findMember = memberDao.getMemberById(request.getUserId());
        Long reservationId = reservationDao.addReservation(new Reservation(request.getProductId(), request.getDisplayInfoId(),
                findMember.getName(), findMember.getTel(), findMember.getEmail(),
                LocalDate.parse(request.getReservationYearMonthDay(), DateTimeFormatter.ofPattern("yyyy.MM.dd")),
                NOT_CANCEL, LocalDate.now(), LocalDate.now()));
        Reservation findReservation = reservationDao.findReservationById(reservationId);
        List<ReservationPrice> findReservationPrices = saveReservationPrices(request, reservationId);
        return new RegistrationResponse(findReservation.getId(), findReservation.getProductId(),
                findReservation.getCancelFlag(), findReservation.getDisplayInfoId(), findMember.getId(),
                Date.valueOf(findReservation.getReservationDate()), Date.valueOf(findReservation.getCreateDate()),
                Date.valueOf(findReservation.getModifyDate()), findReservationPrices);
    }

    @Transactional
    public List<ReservationPrice> saveReservationPrices(RegistrationRequest request, Long reservationId) {
        List<Long> reservationPriceIds = new ArrayList<>();
        for (ReservationPrice reservationPrice : request.getPrices()) {
            Long reservationPriceId = reservationPriceDao.addReservationPrice(new ReservationPrice(reservationId, reservationPrice.getProductPriceId(), reservationPrice.getCount()));
            reservationPriceIds.add(reservationPriceId);
        }
        return getReservationPrices(reservationPriceIds);
    }

    private List<ReservationPrice> getReservationPrices(List<Long> reservationPriceIds) {
        List<ReservationPrice> findReservationPrices = new ArrayList<>();
        for (Long reservationPriceId : reservationPriceIds) {
            findReservationPrices.add(reservationPriceDao.findReservationPrice(reservationPriceId));
        }
        return findReservationPrices;
    }

    public List<Order> getOrdersByUser(Member member) {
        return reservationDao.findOrdersByUserEmail(member.getEmail());
    }

    @Transactional
    public Map<String, String> cancelOrders(Long reservationId, String loginId) {
        Map<String, String> result = new HashMap<>();
        result.put("result", "fail");
        Optional<Reservation> findReservation = Optional.ofNullable(reservationDao.findReservationById(reservationId));
        if (findReservation.isPresent() && findReservation.get().getReservationEmail().equals(loginId)) {
            Integer success = reservationDao.cancelReservationsById(reservationId);
            if (success == 1) {
                result.put("result", "success");
            }
        }

        return result;
    }
}
