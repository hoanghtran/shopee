package com.act.vn.shopeeApplication.controller;

import com.act.vn.shopeeApplication.common.Constants;
import com.act.vn.shopeeApplication.dto.request.CategoryRequest;
import com.act.vn.shopeeApplication.dto.response.ApiResponse;
import com.act.vn.shopeeApplication.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/public/categories")
    public ApiResponse getCategories(@RequestParam(name = "pageNumber", defaultValue = Constants.PAGE_NUMBER) Integer pageNumber,
                                     @RequestParam(name = "pageSize", defaultValue = Constants.PAGE_SIZE) Integer pageSize,
                                     @RequestParam(name = "sortBy", defaultValue = Constants.CATEGORY_SORT_BY_NAME) String SortByName,
                                     @RequestParam(name = "sortOrder", defaultValue = Constants.CATEGORY_SORT_ORDER) String sortOrder) {
        return categoryService.getAllCategories(pageNumber, pageSize, SortByName, sortOrder);
    }

    @GetMapping("/public/categories/{id}")
    public ApiResponse getCategoryById(@PathVariable String id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<ApiResponse> createNewCategory(@Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.createNewCategory(request));
    }

    @PutMapping("/admin/categories/{id}")
    public ResponseEntity<ApiResponse> updateCategoryById(@RequestBody CategoryRequest request, @PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryService.updateCategoryById(request, id));
    }

    @DeleteMapping("/admin/categories/{id}")
    public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.deleteCategoryById(id));
    }
}
