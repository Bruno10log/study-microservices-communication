package com.bruno10log.productapi.modules.supplier.model;


import com.bruno10log.productapi.modules.supplier.dto.SupplierRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SUPPLIER")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    Supplier(String name) {
        this.setName(name);
    }

    public static Supplier of(SupplierRequest request) {
        return new Supplier(request.name());
    }

}
