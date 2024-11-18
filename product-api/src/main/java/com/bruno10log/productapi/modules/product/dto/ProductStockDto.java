package com.bruno10log.productapi.modules.product.dto;

import java.util.List;

public record ProductStockDto(String salesId, List<ProductQuantityDto> products) {
}
