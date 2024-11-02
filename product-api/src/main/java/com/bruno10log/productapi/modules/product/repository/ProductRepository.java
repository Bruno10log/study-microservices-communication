package com.bruno10log.productapi.modules.product.repository;

import com.bruno10log.productapi.modules.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
