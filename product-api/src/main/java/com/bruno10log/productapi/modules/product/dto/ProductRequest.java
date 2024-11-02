package com.bruno10log.productapi.modules.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record ProductRequest(Integer id,
                             String name,
                             Integer categoryId,
                             Integer supplierId,
                             @JsonProperty("quantity_available")
                             Integer quantityAvailable,
                             @JsonProperty("created_at")
                             @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                             LocalDateTime createdAt) {

}
