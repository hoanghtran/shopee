package com.act.vn.shopeeApplication.mapper;

import com.act.vn.shopeeApplication.dto.response.CategoryResponse;
import com.act.vn.shopeeApplication.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponse mapCategoryToDto(Category category){
        return new CategoryResponse(category.getId(), category.getName());
    }
}
