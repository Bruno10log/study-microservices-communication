package com.bruno10log.productapi.modules.supplier.repository;

import com.bruno10log.productapi.modules.supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}