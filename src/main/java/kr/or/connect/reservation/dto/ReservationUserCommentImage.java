package kr.or.connect.reservation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationUserCommentImage {
    private Long reservationUserCommentImageId;
    private Long reservationUserCommentId;
    private Long fileId;
}
