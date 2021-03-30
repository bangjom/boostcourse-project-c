package kr.or.connect.reservation.vo;

import kr.or.connect.reservation.dto.ReservationUserComment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class CommentVo {
    private Integer reservationInfoId;
    private Integer score;
    private String comment;

    public ReservationUserComment toComment(Long productId, Long userId) {
        return ReservationUserComment.builder()
                .productId(productId)
                .reservationInfoId(this.reservationInfoId.longValue())
                .score(this.score)
                .userId(userId)
                .comment(this.comment)
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentVo commentVo = (CommentVo) o;
        return Objects.equals(reservationInfoId, commentVo.reservationInfoId) && Objects.equals(score, commentVo.score) && Objects.equals(comment, commentVo.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationInfoId, score, comment);
    }
}