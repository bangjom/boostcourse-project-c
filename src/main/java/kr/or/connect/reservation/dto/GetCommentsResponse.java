package kr.or.connect.reservation.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetCommentsResponse implements GetDisplayInfosResponse {
    private int totalcount;
    private int commenctCount;
    private List<ReservationUserComment> reservationUserComments;
}
