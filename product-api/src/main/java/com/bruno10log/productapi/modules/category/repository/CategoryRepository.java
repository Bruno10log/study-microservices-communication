package com.bruno10log.productapi.modules.category.repository;

import com.bruno10log.productapi.modules.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
