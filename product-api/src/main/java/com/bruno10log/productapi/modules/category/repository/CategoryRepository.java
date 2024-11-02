package com.bruno10log.productapi.modules.category.repository;

import com.bruno10log.productapi.modules.category.dto.CategoryResponse;
import com.bruno10log.productapi.modules.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<CategoryResponse> findByDescriptionIgnoreCaseContaining(String description);

}
