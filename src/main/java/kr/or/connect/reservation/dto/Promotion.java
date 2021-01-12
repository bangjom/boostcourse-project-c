package kr.or.connect.reservation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Promotion {
    private Long id;
    private Long productId;
    private Long categoryId;
    private String categoryName;
    private String description;
    private Long fileId;
}
