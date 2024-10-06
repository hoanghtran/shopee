package com.act.vn.shopeeApplication.service.impl;

import com.act.vn.shopeeApplication.dto.request.CategoryRequest;
import com.act.vn.shopeeApplication.dto.response.ApiResponse;
import com.act.vn.shopeeApplication.dto.response.CategoryResponse;
import com.act.vn.shopeeApplication.entity.Category;
import com.act.vn.shopeeApplication.mapper.CategoryMapper;
import com.act.vn.shopeeApplication.repository.CategoryRepository;
import com.act.vn.shopeeApplication.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper mapper;

    public ApiResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> responseCategoryList = categories.stream()
                .map(mapper::mapCategoryToDto)
                .collect(Collectors.toList());

        return ApiResponse.builder()
                .message("Get all categories successfully")
                .status(HttpStatus.OK)
                .body(responseCategoryList)
                .build();
    }

    public ApiResponse createNewCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());

        Category saved = categoryRepository.save(category);
        return ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .message("Category created successfully")
                .body(mapper.mapCategoryToDto(category))
                .build();
    }

    public ApiResponse getCategoryById(String id) {
        Category foundCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with " + id + " not found"));
        return ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Get category id " + id + " successfully")
                .body(mapper.mapCategoryToDto(foundCategory))
                .build();
    }

    public ApiResponse updateCategoryById(CategoryRequest request, String id) {
        Category foundCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find category " + id));

        Category updatedCategory = foundCategory;
        updatedCategory.setName(request.getName());
        categoryRepository.save(updatedCategory);

        return ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Category " + id + " updated successfully")
                .body(mapper.mapCategoryToDto(updatedCategory))
                .build();
    }

    public ApiResponse deleteCategoryById(String id) {
        categoryRepository.deleteById(id);
        return ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Category " + id + " deleted successfully")
                .build();
    }
}
