package org.example.multitenant.repository.dao.product;

import org.example.multitenant.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

