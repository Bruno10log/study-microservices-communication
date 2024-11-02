package com.bruno10log.productapi.modules.category.model;

import com.bruno10log.productapi.modules.category.dto.CategoryRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    Category(String description) {
        this.setDescription(description);
    }

    public static Category of(CategoryRequest request) {
       return new Category(request.description());
    }

}
