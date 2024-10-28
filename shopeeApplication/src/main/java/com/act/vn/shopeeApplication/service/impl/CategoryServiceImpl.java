package com.act.vn.shopeeApplication.service.impl;

import com.act.vn.shopeeApplication.common.Constants;
import com.act.vn.shopeeApplication.dto.request.CategoryRequest;
import com.act.vn.shopeeApplication.dto.response.ApiResponse;
import com.act.vn.shopeeApplication.dto.response.CategoryItemResponse;
import com.act.vn.shopeeApplication.dto.response.CategoryResponse;
import com.act.vn.shopeeApplication.entity.Category;
import com.act.vn.shopeeApplication.exception.NotFoundException;
import com.act.vn.shopeeApplication.mapper.CategoryMapper;
import com.act.vn.shopeeApplication.repository.CategoryRepository;
import com.act.vn.shopeeApplication.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public ApiResponse getAllCategories(int pageNumber, int pageSize, String sortByName, String sortOrder) {

        Sort sortByAndOrder = sortOrder.equals(Constants.CATEGORY_SORT_ORDER) ? Sort.by(sortByName).ascending() : Sort.by(sortByName).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        List<Category> categories = categoryPage.getContent();

        List<CategoryItemResponse> list = categories.stream()
                .map(mapper::mapCategoryToDto)
                .collect(Collectors.toList());

        CategoryResponse categoryResponse = getCategoryResponse(list, categoryPage);

        return ApiResponse.builder()
                .message("Get all categories successfully")
                .status(HttpStatus.OK)
                .body(categoryResponse)
                .build();
    }

    private static CategoryResponse getCategoryResponse(List<CategoryItemResponse> responseCategoryList, Page<Category> categoryPage) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContext(responseCategoryList);
        categoryResponse.setPageNumber(categoryPage.getNumber() + 1);
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getNumberOfElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setFirstPage(categoryPage.isFirst());
        categoryResponse.setLastPage(categoryPage.isLast());
        return categoryResponse;
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
        Category category = findCategoryById(id);
        return ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Get category id " + id + " successfully")
                .body(mapper.mapCategoryToDto(category))
                .build();
    }

    public ApiResponse updateCategoryById(CategoryRequest request, String id) {
        Category category = findCategoryById(id);
        category.setName(request.getName());
        categoryRepository.save(category);

        return ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Category " + id + " updated successfully")
                .body(mapper.mapCategoryToDto(category))
                .build();
    }

    public ApiResponse deleteCategoryById(String id) {
        Category category = findCategoryById(id);
        categoryRepository.delete(category);
        return ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Category " + id + " deleted successfully")
                .build();
    }

    public Category findCategoryById(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category with " + id + " not found"));
        return category;
    }
}
