package com.bruno10log.productapi.modules.supplier.controller;

import com.bruno10log.productapi.config.exception.SuccessResponse;
import com.bruno10log.productapi.modules.supplier.dto.SupplierRequest;
import com.bruno10log.productapi.modules.supplier.dto.SupplierResponse;
import com.bruno10log.productapi.modules.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public SupplierResponse save(@RequestBody SupplierRequest request) {
        return supplierService.save(request);
    }

    @GetMapping
    public List<SupplierResponse> findAll() {
        return supplierService.findAll();
    }

    @GetMapping("{id}")
    public SupplierResponse findById(@PathVariable Integer id) {
        return supplierService.findByIdResponse(id);
    }

    @GetMapping("description/{description}")
    public List<SupplierResponse> findByDescription(@PathVariable String description) {
        return supplierService.findByDescription(description);
    }

    @PutMapping("{id}")
    public SupplierResponse update(@RequestBody SupplierRequest request,
                                   @PathVariable Integer id) {
        return supplierService.update(request, id);
    }

    @DeleteMapping("{id}")
    public SuccessResponse delete(@PathVariable Integer id) {
        return supplierService.delete(id);
    }

}
