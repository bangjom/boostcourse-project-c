package kr.or.connect.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {
    private Long id;
    private Long productId;
    private Integer cancelFlag;
    private Long displayInfoId;
    private Long userId;
    private Date reservationDate;
    private Date createDate;
    private Date modifyDate;
    private List<ReservationPrice> prices;
}
