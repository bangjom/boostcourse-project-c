package kr.or.connect.reservation.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.dto.CommentsResponse;
import kr.or.connect.reservation.dto.DisplayIdResponse;
import kr.or.connect.reservation.dto.ProductsResponse;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.dto.ReservationUserComment;
import kr.or.connect.reservation.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/displayinfos")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @ApiOperation(value = "상품목록 가져오기")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 500, message = "Exception")
    })
    @GetMapping
    public ProductsResponse getProducts(
        @RequestParam(defaultValue = "0") long categoryId,
        @RequestParam(defaultValue = "0") int start
    ) {
        if (categoryId == 0) {
            int totalCount = productService.getCount();
            List<Product> products = productService.getProducts(start);
            return new ProductsResponse(totalCount, products.size(), products);
        }
        int totalCount = productService.getCountByCategory(categoryId);
        List<Product> products = productService.getProductsByCategory(categoryId, start);
        return new ProductsResponse(totalCount, products.size(), products);
    }

    @ApiOperation(value = "댓글 목록 가져오기")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 500, message = "Exception")
    })
    @GetMapping("/comments")
    public CommentsResponse getComments(
        @RequestParam(defaultValue = "0") long productId,
        @RequestParam(defaultValue = "0") int start
    ) {
        int totalCount = productService.getReservationUserCommentsCountByProductId(productId);
        List<ReservationUserComment> comments = productService
            .getReservationUserCommentsWithImagesByProductId(productId, start);
        return new CommentsResponse(totalCount, comments.size(), comments);
    }

    @ApiOperation(value = "상품 상세정보 가져오기")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 500, message = "Exception")
    })
    @GetMapping("/{displayId}")
    public DisplayIdResponse getProductDetails(@PathVariable Long displayId) {
        Product product = productService.getProductByDisplayId(displayId);
        List<ProductImage> productImages = productService
            .getProductImageByProductId(product.getId());
        List<DisplayInfoImage> displayInfoImages = productService
            .getDisplayInfoImageByDisplayId(displayId);
        int averageScore = productService.getAverageScoreByProductId(product.getId());
        List<ProductPrice> productPrices = productService
            .getProductPricesByProductId(product.getId());

        return new DisplayIdResponse(product, productImages, displayInfoImages, averageScore,
            productPrices);
    }
}
