package kr.or.connect.reservation.controller;


import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.config.WebAppInitializer;
import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.PostCommentsResponse;
import kr.or.connect.reservation.service.CommentService;
import kr.or.connect.reservation.service.ProductService;
import kr.or.connect.reservation.vo.CommentVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppInitializer.class, ApplicationConfig.class})
public class CommentControllerTest {

    @InjectMocks
    public CommentController commentController;


    @Mock
    CommentService commentService;

    @Mock
    ProductService productService;

    private MockMvc mockMvc;

    @Before
    public void createController() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

    @Test
    public void postCommentsTest() throws Exception {
        //given
        Principal principal = Mockito.mock(Principal.class);
        MockMultipartFile file = new MockMultipartFile("multipartFile", "test.txt", "text/plain", "hello file".getBytes());
        PostCommentsResponse response = new PostCommentsResponse("success", 1);
        CommentVo commentVo = new CommentVo();
        commentVo.setComment("test");
        commentVo.setScore(3);
        commentVo.setReservationInfoId(17);

        //when
        when(commentService.postComments(file, commentVo, principal)).thenReturn(response);
        mockMvc.perform(multipart("/api/comments").file(file)
                .param("comment", "test")
                .param("score", "3")
                .param("reservationInfoId", "17")
                .principal(principal))
                .andExpect(status().isOk())
                .andDo(print());

        //then
        verify(commentService).postComments(file, commentVo, principal);
    }

    @Test
    public void getCommentsTest() throws Exception {
        //given
        Comment comment = Comment.builder()
                .id(1L)
                .productId(1L)
                .reservationInfoId(1L)
                .score(3)
                .userId(1L)
                .comment("test")
                .build();
        List<Comment> comments = Arrays.asList(comment);
        Long productId = 0L;
        int start = 0;
        //when
        when(productService.getCommentsWithImagesByProductId(productId, start)).thenReturn(comments);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/comments")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print());

        //then
        verify(productService).getCommentsWithImagesByProductId(productId,start);
    }
}
