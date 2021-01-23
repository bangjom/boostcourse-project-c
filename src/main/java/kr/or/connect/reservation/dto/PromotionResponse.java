package kr.or.connect.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PromotionResponse<T> {
    private int size;
    private T items;
}
