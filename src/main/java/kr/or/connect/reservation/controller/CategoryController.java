package kr.or.connect.reservation.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.GetResponse;
import kr.or.connect.reservation.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @ApiOperation(value = "카테고리 가져오기")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 500, message = "Exception")
    })
    @GetMapping
    public GetResponse get() {
        int count = categoryService.getCount();
        List<Category> categories = categoryService.getCategories();

        return new GetResponse(count, categories);
    }
}
