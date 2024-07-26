package org.example.productservice.repository;

import org.example.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

//public interface ProductRepository extends MongoRepository<Product, String> {
//}

public interface ProductRepository extends JpaRepository<Product, Long> {
}

