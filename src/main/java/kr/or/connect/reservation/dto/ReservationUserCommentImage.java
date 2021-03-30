package kr.or.connect.reservation.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationUserCommentImage {
    @JsonProperty("id")
    private Long imageId;
    private Long reservationInfoId;
    private Long reservationUserCommentId;
    private Long fileId;
    private String fileName;
    private String saveFileName;
    private String contentType;
    private Integer deleteFlag;
    private Date createDate;
    private Date modifyDate;
}
