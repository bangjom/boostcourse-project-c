package kr.or.connect.reservation.controller;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.PromotionService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class PromotionControllerTest {
    @InjectMocks
    PromotionController promotionController;

    @Mock
    PromotionService promotionService;

    private MockMvc mockMvc;

    @Before
    public void createController() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(promotionController).build();
    }

    @Test
    public void getPromotionsTest() throws Exception {
        //given
        Promotion promotion = new Promotion();
        promotion.setId(1L);
        promotion.setProductId(1L);
        promotion.setCategoryId(1L);
        promotion.setCategoryName("test");
        promotion.setDescription("test description");
        promotion.setFileId(1L);
        List<Promotion> promotions = Arrays.asList(promotion);

        //when
        when(promotionService.getPromotions()).thenReturn(promotions);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/promotions")
            .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andDo(print());

        //then
        verify(promotionService).getPromotions();
    }
}