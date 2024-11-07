package com.bruno10log.productapi.modules.product.service;

import com.bruno10log.productapi.config.exception.SuccessResponse;
import com.bruno10log.productapi.config.exception.ValidationException;
import com.bruno10log.productapi.modules.category.service.CategoryService;
import com.bruno10log.productapi.modules.product.dto.ProductRequest;
import com.bruno10log.productapi.modules.product.dto.ProductResponse;
import com.bruno10log.productapi.modules.product.model.Product;
import com.bruno10log.productapi.modules.product.repository.ProductRepository;
import com.bruno10log.productapi.modules.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ProductResponse> findBySupplierId(Integer supplierId) {
        if(isEmpty(supplierId)) {
            throw new ValidationException("The product' supplier ID must be informed.");
        }

        return productRepository.findBySupplierId(supplierId)
                                .stream().map(ProductResponse::of)
                                .collect(Collectors.toList());
    }

    public List<ProductResponse> findByCategoryId(Integer categoryId) {
        if(isEmpty(categoryId)) {
            throw new ValidationException("The product' category ID must be informed.");
        }

        return productRepository.findByCategoryId(categoryId)
                                .stream().map(ProductResponse::of)
                                .collect(Collectors.toList());
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream().map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> findByName(String name) {
        if(isEmpty(name)) {
            throw new ValidationException("The product's name must be informed");
        }

        return productRepository.findByNameIgnoreCaseContaining(name)
                            .stream().map(ProductResponse::of)
                            .collect(Collectors.toList());
    }

    public ProductResponse findById(Integer id) {
        validateInformedId(id);
        var product = productRepository.findById(id)
                                        .orElseThrow(() -> new ValidationException("There is no product for the given ID."));

        return ProductResponse.of(product);
    }

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

    public Boolean existsByCategoryId(Integer categoryId) {
        return productRepository.existsByCategoryId(categoryId);
    }

    public Boolean existsBySupplierId(Integer supplierId) {
        return productRepository.existsBySupplierId(supplierId);
    }

    public SuccessResponse delete(Integer id) {
        validateInformedId(id);
        productRepository.deleteById(id);
        return SuccessResponse.create("The product was deleted");
    }

    public ProductResponse update(ProductRequest request, Integer id) {
        validateProductDataInformed(request);
        validateCategoryAndSupplierIdInformed(request);

        var category = categoryService.findById(request.categoryId());
        var supplier = supplierService.findById(request.supplierId());

        var product = Product.of(request, supplier, category);
        product.setId(id);

        productRepository.save(product);
        return ProductResponse.of(product);
    }

    private void validateInformedId(Integer id) {
        if(isEmpty(id)) {
            throw new ValidationException("The product's ID must be informed");
        }
    }
}

