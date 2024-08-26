package org.example.productservice.model;

import jakarta.persistence.*;
import lombok.*;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.nio.file.FileStore;
import java.util.List;


@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

    @jakarta.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}
