package com.bruno10log.productapi.modules.product.model;

import com.bruno10log.productapi.modules.category.model.Category;
import com.bruno10log.productapi.modules.product.dto.ProductRequest;
import com.bruno10log.productapi.modules.supplier.model.Supplier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "FK_CATEGORY", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "FK_SUPPLIER", nullable = false)
    private Supplier supplier;

    @Column(name = "QUANTITY_AVAILABLE")
    private Integer quantityAvailable;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public Product(String name, Category category, Supplier supplier, Integer quantityAvailable) {
        this.category = category;
        this.supplier = supplier;
        this.quantityAvailable = quantityAvailable;
        this.name = name;
    }

    public static Product of(ProductRequest request, Supplier supplier, Category category) {
        return new Product(request.name(), category,
                            supplier, request.quantityAvailable());
    }

    public void updateStock(Integer quantity) {
        quantityAvailable -= quantity;
    }

}
