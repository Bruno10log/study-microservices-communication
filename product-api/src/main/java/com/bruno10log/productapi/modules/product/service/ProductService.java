package com.bruno10log.productapi.modules.product.service;

import com.bruno10log.productapi.config.exception.ValidationException;
import com.bruno10log.productapi.modules.product.dto.ProductRequest;
import com.bruno10log.productapi.modules.product.dto.ProductResponse;
import com.bruno10log.productapi.modules.product.model.Product;
import com.bruno10log.productapi.modules.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponse save(ProductRequest request) {
        validateCategoryNameInformed(request);
        var product = productRepository.save(Product.of(request));
        return ProductResponse.of(product);
    }

    private void validateCategoryNameInformed(ProductRequest request) {
        if(isEmpty(request.name())) {
            throw new ValidationException("The name was not informed");
        }
    }

}
