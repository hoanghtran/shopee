package com.act.vn.shopeeApplication.controller;

import com.act.vn.shopeeApplication.dto.request.CategoryRequest;
import com.act.vn.shopeeApplication.dto.response.ApiResponse;
import com.act.vn.shopeeApplication.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/public/categories")
    public ApiResponse getCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/public/categories/{id}")
    public ApiResponse getCategoryById(@PathVariable String id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<ApiResponse> createNewCategory
            (@RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.createNewCategory(request));
    }

    @PutMapping("/admin/categories/{id}")
    public ResponseEntity<ApiResponse>
    updateCategoryById(@RequestBody CategoryRequest request, @PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.updateCategoryById(request, id));
    }

    @DeleteMapping("/admin/categories/{id}")
    public ResponseEntity<ApiResponse>
    deleteCategoryById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.deleteCategoryById(id));
    }
}
