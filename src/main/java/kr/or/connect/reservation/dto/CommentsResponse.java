package kr.or.connect.reservation.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentsResponse {
    private int totalCount;
    private int commentCount;
    private List<ReservationUserComment> reservationUserComments;
}
