package kr.or.connect.reservation.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import java.time.LocalDateTime;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationUserComment {
    private Long id;
    private Long productId;
    private Long reservationInfoId;
    private Integer score;
    private String reservationEmail;
    private String comment;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd kk:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createDate;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd kk:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifyDate;
    private List<ReservationUserCommentImage> reservationUserCommentImages;
}
