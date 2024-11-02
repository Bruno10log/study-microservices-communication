package com.bruno10log.productapi.modules.product.dto;

import com.bruno10log.productapi.modules.category.model.Category;
import com.bruno10log.productapi.modules.product.model.Product;
import com.bruno10log.productapi.modules.supplier.model.Supplier;

public record ProductResponse(Integer id, String name, Category category, Supplier supplier, int quantityAvailable) {

    public static ProductResponse of(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getCategory(),
                                        product.getSupplier(), product.getQuantityAvailable());
    }
}
