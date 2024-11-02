package com.bruno10log.productapi.modules.product.dto;

import com.bruno10log.productapi.modules.category.dto.CategoryResponse;
import com.bruno10log.productapi.modules.product.model.Product;
import com.bruno10log.productapi.modules.supplier.dto.SupplierResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record ProductResponse(Integer id,
                              String name,
                              CategoryResponse category,
                              SupplierResponse supplier,
                              @JsonProperty("quantity_available")
                              Integer quantityAvailable,
                              @JsonProperty("created_at")
                              @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                              LocalDateTime createdAt) {

    public static ProductResponse of(Product product) {
        return new ProductResponse(product.getId(),
                                   product.getName(),
                                   CategoryResponse.of(product.getCategory()),
                                   SupplierResponse.of(product.getSupplier()),
                                   product.getQuantityAvailable(),
                                   product.getCreatedAt());
    }
}
