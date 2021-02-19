package kr.or.connect.reservation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegistrationRequest {
    private List<ReservationPrice> prices;
    private Long productId;
    private Long displayInfoId;
    private String reservationYearMonthDay;
    private Long userId;
}
