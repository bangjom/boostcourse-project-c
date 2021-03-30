package kr.or.connect.reservation.dto;


import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private Long id;
    private Long productId;
    private Long displayInfoId;
    private Long userId;
    private LocalDate reservationDate;
    private Integer cancelFlag;
    private LocalDate createDate;
    private LocalDate modifyDate;

    public Reservation(Long productId, Long displayInfoId, Long userId, LocalDate reservationDate, Integer cancelFlag, LocalDate createDate, LocalDate modifyDate) {
        this.productId = productId;
        this.displayInfoId = displayInfoId;
        this.reservationDate = reservationDate;
        this.userId = userId;
        this.cancelFlag = cancelFlag;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }
}
