package com.bruno10log.productapi.modules.category.dto;

import com.bruno10log.productapi.modules.category.model.Category;

public record CategoryResponse(Integer id, String description) {
    public static CategoryResponse of(Category category) {
        return new CategoryResponse(category.getId(), category.getDescription());
    }
}
