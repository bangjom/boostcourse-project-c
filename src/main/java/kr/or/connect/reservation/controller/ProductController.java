package kr.or.connect.reservation.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.dto.GetCommentsResponse;
import kr.or.connect.reservation.dto.GetDisplayIdResponse;
import kr.or.connect.reservation.dto.GetDisplayInfosResponse;
import kr.or.connect.reservation.dto.GetProductsResponse;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.dto.ReservationUserComment;
import kr.or.connect.reservation.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/displayinfos")
public class ProductController {
    @Autowired
    ProductService productService;

    @ApiOperation(value = "상품목록 가져오기")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 500, message = "Exception")
    })
    @GetMapping
    public GetDisplayInfosResponse get(
        @RequestParam(name = "productId", required = false, defaultValue = "0") Long productId,
        @RequestParam(name = "categoryId", required = false, defaultValue = "0") Long categoryId,
        @RequestParam(name = "start", required = false, defaultValue = "0") int start
    ) {
        if (categoryId == 0L && productId != 0L) {
            int totalCount = productService.getReservationUserCommentsCountByProductId(productId);
            List<ReservationUserComment> comments = productService
                .getReservationUserCommentsWithImagesByProductId(productId, start);
            return new GetCommentsResponse(totalCount, comments.size(), comments);
        }
        int totalCount = productService.getCountByCategory(categoryId);
        List<Product> products = productService.getProductsByCategory(categoryId, start);
        return new GetProductsResponse(totalCount, products.size(), products);
    }

    @ApiOperation(value = "상품 상세정보 가져오기")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 500, message = "Exception")
    })
    @GetMapping("/{displayId}")
    public GetDisplayIdResponse get(@PathVariable(name = "displayId") Long displayId) {
        Product product = productService.getProductByDisplayId(displayId);
        List<ProductImage> productImages = productService.getProductImageByProductId(product.getId());
        List<DisplayInfoImage> displayInfoImages = productService.getDisplayInfoImageByDisplayId(displayId);
        int averageScore = productService.getAverageScoreByProductId(product.getId());
        List<ProductPrice> productPrices = productService.getProductPricesByProductId(product.getId());

        return new GetDisplayIdResponse(product, productImages, displayInfoImages, averageScore,
            productPrices);
    }
}
