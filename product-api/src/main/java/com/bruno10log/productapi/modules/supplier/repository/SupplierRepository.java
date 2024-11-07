package com.bruno10log.productapi.modules.supplier.repository;

import com.bruno10log.productapi.modules.supplier.dto.SupplierResponse;
import com.bruno10log.productapi.modules.supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    List<SupplierResponse> findByNameIgnoreCaseContaining(String name);
}