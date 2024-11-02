package com.bruno10log.productapi.modules.supplier.service;

import com.bruno10log.productapi.config.exception.ValidationException;
import com.bruno10log.productapi.modules.supplier.dto.SupplierRequest;
import com.bruno10log.productapi.modules.supplier.dto.SupplierResponse;
import com.bruno10log.productapi.modules.supplier.model.Supplier;
import com.bruno10log.productapi.modules.supplier.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier findById(Integer id) {
        return supplierRepository.findById(id)
                                 .orElseThrow(() -> new ValidationException("There is no supplier for the given ID."));
    }

    public SupplierResponse save(SupplierRequest request) {
        validateSupplierNameInformed(request);
        var supplier = supplierRepository.save(Supplier.of(request));
        return SupplierResponse.of(supplier);
    }

    private void validateSupplierNameInformed(SupplierRequest request) {
        if(isEmpty(request.name())) {
            throw new ValidationException("The supplier's name was not informed");
        }
    }

}
