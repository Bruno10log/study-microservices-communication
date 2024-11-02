package com.bruno10log.productapi.modules.product.dto;

import com.bruno10log.productapi.modules.category.model.Category;
import com.bruno10log.productapi.modules.supplier.model.Supplier;

public record ProductRequest(Integer id, String name, Category category, Supplier supplier, int quantityAvailable) {

}
