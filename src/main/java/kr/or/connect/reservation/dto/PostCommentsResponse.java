package kr.or.connect.reservation.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostCommentsResponse {
    private String result;
    private Integer productId;

    public void updateSuccess(){
        this.result = "success";
    }
}
