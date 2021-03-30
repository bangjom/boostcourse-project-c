package kr.or.connect.reservation.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Comment {
    private Long id;
    private Long productId;
    private Long reservationInfoId;
    private Integer score;
    private Long userId;
    private String comment;
    private List<ReservationUserCommentImage> reservationUserCommentImages;

    public Comment(ReservationUserComment userComment, List<ReservationUserCommentImage> reservationUserCommentImages){
        this.id = userComment.getId();
        this.productId = userComment.getProductId();
        this.reservationInfoId = userComment.getReservationInfoId();
        this.score = userComment.getScore();
        this.userId = userComment.getUserId();
        this.comment = userComment.getComment();
        this.reservationUserCommentImages = reservationUserCommentImages;
    }
}
