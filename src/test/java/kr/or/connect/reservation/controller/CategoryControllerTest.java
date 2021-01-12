package kr.or.connect.reservation.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.config.WebAppInitializer;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.service.CategoryService;

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
public class CategoryControllerTest {

    @InjectMocks
    public CategoryController categoryController;

    @Mock
    CategoryService categoryService;

    private MockMvc mockMvc;

    @Before
    public void createController() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void getCategoriesTest() throws Exception {
        //given
        Category category = new Category();
        category.setId(1L);
        category.setName("전시");
        category.setCount(10);
        List<Category> categories = Arrays.asList(category);

        //when
        when(categoryService.getCategories()).thenReturn(categories);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/categories")
            .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andDo(print());

        //then
        verify(categoryService).getCategories();
    }
}