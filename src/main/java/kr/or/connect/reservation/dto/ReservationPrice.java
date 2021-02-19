package kr.or.connect.reservation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservationPrice {
    private Long id;
    private Long reservationInfoId;
    private Long productPriceId;
    private Integer count;

    public ReservationPrice(Long reservationInfoId, Long productPriceId, Integer count) {
        this.reservationInfoId = reservationInfoId;
        this.productPriceId = productPriceId;
        this.count = count;
    }
}
