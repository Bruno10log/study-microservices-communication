package com.bruno10log.productapi.modules.category.service;

import com.bruno10log.productapi.config.exception.SuccessResponse;
import com.bruno10log.productapi.config.exception.ValidationException;
import com.bruno10log.productapi.modules.category.dto.CategoryRequest;
import com.bruno10log.productapi.modules.category.dto.CategoryResponse;
import com.bruno10log.productapi.modules.category.model.Category;
import com.bruno10log.productapi.modules.category.repository.CategoryRepository;
import com.bruno10log.productapi.modules.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService;

    public CategoryResponse findByIdResponse(Integer id) {
        return CategoryResponse.of(findById(id));
    }

    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll()
                                    .stream().map(CategoryResponse::of)
                                    .collect(Collectors.toList());
    }

    public List<CategoryResponse> findByDescription(String description) {
        if(isEmpty(description)) {
            throw new ValidationException("The category description must be informed");
        }
        return categoryRepository.findByDescriptionIgnoreCaseContaining(description);
    }

    public Category findById(Integer id) {

        validateInformedId(id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ValidationException("There is no category for the given ID."));
    }

    public CategoryResponse save(CategoryRequest request) {
        validateCategoryDescriptionInformed(request);
        var category = categoryRepository.save(Category.of(request));
        return CategoryResponse.of(category);
    }

    public CategoryResponse update(CategoryRequest request, Integer id) {
        validateCategoryDescriptionInformed(request);
        var category = Category.of(request);
        category.setId(id);
        categoryRepository.save(category);
        return CategoryResponse.of(category);
    }


    private void validateCategoryDescriptionInformed(CategoryRequest request) {
        if(isEmpty(request.description())) {
            throw new ValidationException("The category description was not informed");
        }
    }

    public SuccessResponse delete(Integer id) {
        validateInformedId(id);

        if(productService.existsByCategoryId(id)) {
            throw new ValidationException("You cannot delete this category because it's already defined by a product.");
        }

        categoryRepository.deleteById(id);
        return SuccessResponse.create("The category was deleted.");
    }

    private void validateInformedId(Integer id) {
        if(isEmpty(id)) {
            throw new ValidationException("The category ID must be informed");
        }
    }
}

