package org.example.multitenant.repository.dao.testproduct;

import org.example.multitenant.model.testproduct.TestProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestProductRepository extends JpaRepository<TestProduct, Long> {
}

