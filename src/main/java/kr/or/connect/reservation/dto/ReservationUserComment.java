package kr.or.connect.reservation.dto;



import java.time.LocalDateTime;

import java.util.List;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ReservationUserComment {
    private Long id;
    private Long productId;
    private Long reservationInfoId;
    private Integer score;
    private Long userId;
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private List<ReservationUserCommentImage> reservationUserCommentImages;
}
