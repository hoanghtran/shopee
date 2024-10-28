package com.act.vn.shopeeApplication.mapper;

import com.act.vn.shopeeApplication.dto.response.CategoryItemResponse;
import com.act.vn.shopeeApplication.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryItemResponse mapCategoryToDto(Category category){
        return new CategoryItemResponse(category.getId(), category.getName());
    }
}
