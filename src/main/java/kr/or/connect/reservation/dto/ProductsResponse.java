package kr.or.connect.reservation.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductsResponse {
    private int totalCount;
    private int productCount;
    private List<Product> products;
}
