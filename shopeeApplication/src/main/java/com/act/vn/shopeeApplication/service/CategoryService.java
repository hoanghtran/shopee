package com.act.vn.shopeeApplication.service;

import com.act.vn.shopeeApplication.dto.request.CategoryRequest;
import com.act.vn.shopeeApplication.dto.response.ApiResponse;

public interface CategoryService {

    ApiResponse getAllCategories(int pageNumber, int pageSize, String sortBy, String sortOrder);

    ApiResponse getCategoryById(String id);

    ApiResponse createNewCategory(CategoryRequest categoryRequest);

    ApiResponse updateCategoryById(CategoryRequest request, String id);

    ApiResponse deleteCategoryById(String id);

}
