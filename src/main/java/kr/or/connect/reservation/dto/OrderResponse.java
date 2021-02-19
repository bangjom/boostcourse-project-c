package kr.or.connect.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponse {
    private int size;
    private List<Order> items;
}
