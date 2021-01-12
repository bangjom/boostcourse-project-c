package kr.or.connect.reservation.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.config.WebAppInitializer;
import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.service.ProductService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppInitializer.class, ApplicationConfig.class})
public class ProductControllerTest extends TestCase {

    @InjectMocks
    public ProductController productController;

    @Mock
    ProductService productService;

    private MockMvc mockMvc;

    @Before
    public void createController() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void getProductsTest() throws Exception {
        //given
        Long categoryId = 0L;
        int start = 0;
        Product product = new Product();
        product.setId(1L);
        product.setCategoryId(1L);
        product.setDisplayInfoId(1L);
        product.setName("kbj");
        product.setDescription("test");
        product.setCreateDate(LocalDateTime.now());
        product.setModifyDate(LocalDateTime.now());
        List<Product> products = Arrays.asList(product);

        //when
        when(productService.getProductsByCategory(categoryId, start)).thenReturn(products);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/displayinfos")
            .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andDo(print());

        //then
        verify(productService).getProductsByCategory(categoryId, start);
    }

    @Test
    public void getProductDetailTest() throws  Exception{
        //given
        Long displayId = 1L;
        Long productId = 1L;
        Product product = new Product();
        product.setId(1L);
        product.setCategoryId(1L);
        product.setDisplayInfoId(1L);
        product.setName("kbj");
        product.setDescription("test");
        product.setContent("content");
        product.setEvent("email");
        product.setOpeningHours("hours");
        product.setPlaceName("house");
        product.setPlaceLot("seoul");
        product.setPlaceStreet("street");
        product.setTel("010-1234-1234");
        product.setHomepage("www.naver.com");
        product.setEmail("email");
        product.setCreateDate(LocalDateTime.now());
        product.setModifyDate(LocalDateTime.now());
        product.setFileId(1L);
        ProductImage productImage = new ProductImage();
        productImage.setProductId(1L);
        productImage.setProductImageId(1L);
        productImage.setType("ma");
        productImage.setFileInfoId(1L);
        productImage.setFileName("filename");
        productImage.setSaveFileName("filename");
        productImage.setContentType("image/png");
        productImage.setDeleteFlag(0);
        productImage.setCreateDate(LocalDateTime.now());
        productImage.setModifyDate(LocalDateTime.now());
        List<ProductImage> productImages = Arrays.asList(productImage);
        DisplayInfoImage displayInfoImage = new DisplayInfoImage();
        displayInfoImage.setId(1L);
        displayInfoImage.setDisplayInfoId(1L);
        displayInfoImage.setFileId(1L);
        displayInfoImage.setFileName("filename");
        displayInfoImage.setSaveFileName("filename");
        displayInfoImage.setContentType("image/png");
        displayInfoImage.setDeleteFlag(0);
        displayInfoImage.setCreateDate(LocalDateTime.now());
        displayInfoImage.setModifyDate(LocalDateTime.now());
        List<DisplayInfoImage> displayInfoImages = Arrays.asList(displayInfoImage);
        int averageScore = 3;
        ProductPrice productPrice = new ProductPrice();
        productPrice.setId(1L);
        productPrice.setProductId(1L);
        productPrice.setPriceTypeName("B");
        productPrice.setPrice(2000);
        productPrice.setDiscountRate(50);
        productPrice.setCreateDate(LocalDateTime.now());
        productPrice.setModifyDate(LocalDateTime.now());
        List<ProductPrice> productPrices = Arrays.asList(productPrice);

        //when
        when(productService.getProductByDisplayId(displayId)).thenReturn(product);
        when(productService.getProductImageByProductId(productId)).thenReturn(productImages);
        when(productService.getDisplayInfoImageByDisplayId(displayId)).thenReturn(displayInfoImages);
        when(productService.getAverageScoreByProductId(productId)).thenReturn(averageScore);
        when(productService.getProductPricesByProductId(productId)).thenReturn(productPrices);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/displayinfos/1")
            .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andDo(print());

        //then
        verify(productService).getProductByDisplayId(displayId);
        verify(productService).getProductImageByProductId(productId);
        verify(productService).getDisplayInfoImageByDisplayId(displayId);
        verify(productService).getAverageScoreByProductId(productId);
        verify(productService).getProductPricesByProductId(productId);
    }
}