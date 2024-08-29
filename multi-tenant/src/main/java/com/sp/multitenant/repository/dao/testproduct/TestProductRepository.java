package com.sp.multitenant.repository.dao.testproduct;

import com.sp.multitenant.model.testproduct.TestProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestProductRepository extends JpaRepository<TestProduct, Long> {
}

