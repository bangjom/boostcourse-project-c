package kr.or.connect.reservation.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.CommentsResponse;
import kr.or.connect.reservation.dto.PostCommentsResponse;
import kr.or.connect.reservation.service.CommentService;
import kr.or.connect.reservation.service.ProductService;
import kr.or.connect.reservation.vo.CommentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final ProductService productService;

    @ApiOperation(value = "댓글 등록하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Exception")
    })
    @PostMapping
    public PostCommentsResponse postComments(@RequestParam("multipartFile") MultipartFile multipartFile, CommentVo commentVo, Principal principal){
        return commentService.postComments(multipartFile,commentVo,principal);
    }

    @ApiOperation(value = "댓글 목록 구하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Exception")
    })
    @GetMapping
    public CommentsResponse getComments(
            @RequestParam(defaultValue = "0") long productId,
            @RequestParam(defaultValue = "0") int start
    ) {
        int totalCount = productService.getReservationUserCommentsCountByProductId(productId);
        List<Comment> comments = productService
                .getCommentsWithImagesByProductId(productId, start);
        return new CommentsResponse(totalCount, comments.size(), comments);
    }

}
