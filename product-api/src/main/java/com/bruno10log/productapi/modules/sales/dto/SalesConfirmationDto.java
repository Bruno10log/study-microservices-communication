package com.bruno10log.productapi.modules.sales.dto;


import com.bruno10log.productapi.modules.sales.enums.SalesStatus;

public record SalesConfirmationDto(String salesId, SalesStatus status) {
}
