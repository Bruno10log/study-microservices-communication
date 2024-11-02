package com.bruno10log.productapi.modules.product.service;

import com.bruno10log.productapi.config.exception.ValidationException;
import com.bruno10log.productapi.modules.category.service.CategoryService;
import com.bruno10log.productapi.modules.product.dto.ProductRequest;
import com.bruno10log.productapi.modules.product.dto.ProductResponse;
import com.bruno10log.productapi.modules.product.model.Product;
import com.bruno10log.productapi.modules.product.repository.ProductRepository;
import com.bruno10log.productapi.modules.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class ProductService {

    private static final int ZERO = 0;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private CategoryService categoryService;

    public ProductResponse save(ProductRequest request) {
        validateProductDataInformed(request);
        validateCategoryAndSupplierIdInformed(request);
        var category = categoryService.findById(request.categoryId());
        var supplier = supplierService.findById(request.supplierId());
        var product = productRepository.save(Product.of(request, supplier, category));
        return ProductResponse.of(product);
    }

    private void validateProductDataInformed(ProductRequest request) {
        if(isEmpty(request.name())) {
            throw new ValidationException("The name was not informed");
        }

        if(isEmpty(request.quantityAvailable())) {
            throw new ValidationException("The product's quantity was not infomed");
        }

        if(request.quantityAvailable() < ZERO) {
            throw new ValidationException("The quantity should not be less or equal to zero.");
        }
    }

    private void validateCategoryAndSupplierIdInformed(ProductRequest request) {

        if(isEmpty(request.categoryId())) {
            throw new ValidationException("The category ID was not informed.");
        }

        if(isEmpty(request.supplierId())) {
            throw new ValidationException("The supplier ID was not informed.");
        }


    }

}
