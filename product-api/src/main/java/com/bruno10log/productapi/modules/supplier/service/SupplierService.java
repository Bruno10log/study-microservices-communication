package com.bruno10log.productapi.modules.supplier.service;

import com.bruno10log.productapi.config.exception.SuccessResponse;
import com.bruno10log.productapi.config.exception.ValidationException;
import com.bruno10log.productapi.modules.product.service.ProductService;
import com.bruno10log.productapi.modules.supplier.dto.SupplierRequest;
import com.bruno10log.productapi.modules.supplier.dto.SupplierResponse;
import com.bruno10log.productapi.modules.supplier.model.Supplier;
import com.bruno10log.productapi.modules.supplier.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductService productService;

    public List<SupplierResponse> findAll() {
        return supplierRepository.findAll()
                .stream().map(SupplierResponse::of)
                .collect(Collectors.toList());
    }

    public List<SupplierResponse> findByDescription(String name) {
        if(isEmpty(name)) {
            throw new ValidationException("The supplier's name must be informed");
        }
        return supplierRepository.findByNameIgnoreCaseContaining(name);
    }

    public Supplier findById(Integer id) {

        validateInformedId(id);
        var supplier = supplierRepository.findById(id)
                                 .orElseThrow(() -> new ValidationException("There is no supplier for the given ID."));
        return supplier;
    }

    public SupplierResponse findByIdResponse(Integer id) {
        return SupplierResponse.of(findById(id));
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

    public SuccessResponse delete(Integer id) {
        validateInformedId(id);

        if(productService.existsBySupplierId(id)) {
            throw new ValidationException("You cannot delete this supplier because it's already defined by a product.");
        }

        supplierRepository.deleteById(id);
        return SuccessResponse.create("The supplier was deleted.");
    }

    public SupplierResponse update(SupplierRequest request, Integer id) {
        validateSupplierNameInformed(request);
        var supplier = Supplier.of(request);
        supplier.setId(id);
        supplierRepository.save(supplier);
        return SupplierResponse.of(supplier);
    }

    private void validateInformedId(Integer id) {
        if(isEmpty(id)) {
            throw new ValidationException("The supplier ID must be informed");
        }
    }

}
