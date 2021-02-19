package kr.or.connect.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private Long productId;
    private Long displayInfoId;
    private Integer cancelFlag;
    private String productDescription;
    private String productContent;
    private Long userId;
    private Integer sumPrice;
    private Date reservationDate;
    private Date createDate;
    private Date modifyDate;
}
