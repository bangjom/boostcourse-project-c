package kr.or.connect.reservation.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DisplayIdResponse {
    private Product product;
    private List<ProductImage> productImages;
    private List<DisplayInfoImage> displayInfoImages;
    private int avgScore;
    private List<ProductPrice> productPrices;
}
