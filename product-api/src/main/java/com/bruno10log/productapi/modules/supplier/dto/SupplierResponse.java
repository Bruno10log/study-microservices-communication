package com.bruno10log.productapi.modules.supplier.dto;

import com.bruno10log.productapi.modules.supplier.model.Supplier;

public record SupplierResponse(Integer id, String name) {
    public static SupplierResponse of(Supplier supplier) {
        return new SupplierResponse(supplier.getId(), supplier.getName());
    }
}
