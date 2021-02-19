package kr.or.connect.reservation.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private Long id;
    private Long productId;
    private Long displayInfoId;
    private String reservationName;
    private String reservationTel;
    private String reservationEmail;
    private LocalDate reservationDate;
    private Integer cancelFlag;
    private LocalDate createDate;
    private LocalDate modifyDate;

    public Reservation(Long productId, Long displayInfoId, String reservationName,String reservationTel, String reservationEmail, LocalDate reservationDate, Integer cancelFlag, LocalDate createDate, LocalDate modifyDate) {
        this.productId = productId;
        this.displayInfoId = displayInfoId;
        this.reservationName = reservationName;
        this.reservationTel = reservationTel;
        this.reservationEmail = reservationEmail;
        this.reservationDate = reservationDate;
        this.cancelFlag = cancelFlag;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }
}
