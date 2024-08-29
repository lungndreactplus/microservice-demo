package com.sp.multitenant.repository.dao.product;

import com.sp.multitenant.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

