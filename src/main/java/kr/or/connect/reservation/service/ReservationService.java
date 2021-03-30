package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dao.UserDao;
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
    private final UserDao userDao;

    private final int NOT_CANCEL = 0;

    @Transactional
    public RegistrationResponse reserve(RegistrationRequest request) {
        User findUser = userDao.getMemberById(request.getUserId());

        Long reservationId = reservationDao.addReservation(
                Reservation.builder()
                        .productId(request.getProductId())
                        .displayInfoId(request.getDisplayInfoId())
                        .userId(findUser.getId())
                        .reservationDate(LocalDate.parse(request.getReservationYearMonthDay(), DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                        .cancelFlag(NOT_CANCEL)
                        .createDate(LocalDate.now())
                        .modifyDate(LocalDate.now())
                        .build());

        Reservation findReservation = reservationDao.findReservationById(reservationId);
        List<ReservationPrice> findReservationPrices = saveReservationPrices(request, reservationId);

        return RegistrationResponse.builder()
                .id(findReservation.getId())
                .productId(findReservation.getProductId())
                .cancelFlag(findReservation.getCancelFlag())
                .displayInfoId(findReservation.getDisplayInfoId())
                .userId(findUser.getId())
                .reservationDate(Date.valueOf(findReservation.getReservationDate()))
                .createDate(Date.valueOf(findReservation.getCreateDate()))
                .modifyDate(Date.valueOf(findReservation.getModifyDate()))
                .prices(findReservationPrices)
                .build();
    }


    private List<ReservationPrice> saveReservationPrices(RegistrationRequest request, Long reservationId) {
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

    public List<Order> getOrdersByUser(User user) {
        return reservationDao.findOrdersByUserEmail(user.getEmail());
    }

    @Transactional
    public Map<String, String> cancelOrders(Long reservationId, String loginId) {
        Map<String, String> result = new HashMap<>();
        result.put("result", "fail");
        User findUser = userDao.getMemberByEmail(loginId);
        Integer success = reservationDao.cancelReservationsById(reservationId, findUser.getId());
        if (success == 1) {
            result.put("result", "success");
        }
        return result;
    }
}
