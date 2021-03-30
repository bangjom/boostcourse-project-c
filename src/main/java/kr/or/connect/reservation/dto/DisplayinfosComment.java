package kr.or.connect.reservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DisplayinfosComment {
    private Long id;
    private Long productId;
    private Long reservationInfoId;
    private Integer score;
    private String reservationEmail;
    private String comment;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd kk:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd kk:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifyDate;
    private List<ReservationUserCommentImage> reservationUserCommentImages;

    public DisplayinfosComment(ReservationUserComment reservationUserComment, List<ReservationUserCommentImage> reservationUserCommentImages, String email) {
        this.id = reservationUserComment.getId();
        this.productId = reservationUserComment.getProductId();
        this.reservationInfoId = reservationUserComment.getReservationInfoId();
        this.score = reservationUserComment.getScore();
        this.reservationEmail = email;
        this.comment = reservationUserComment.getComment();
        this.createDate = reservationUserComment.getCreateDate();
        this.modifyDate = reservationUserComment.getModifyDate();
        this.reservationUserCommentImages = reservationUserCommentImages;
    }
}
