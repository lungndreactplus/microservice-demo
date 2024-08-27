package com.sp.multitenant.service;

import com.sp.multitenant.dto.ProductRequest;
import com.sp.multitenant.dto.ProductResponse;
import com.sp.multitenant.model.testproduct.TestProduct;
import com.sp.multitenant.repository.dao.testproduct.TestProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestProductService {

    private final TestProductRepository testproductRepository;

    public void createTestProduct(ProductRequest productRequest) {
        TestProduct testproduct = TestProduct.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        testproductRepository.save(testproduct);
        log.info("Product {} is saved", testproduct.getId());
    }

    public List<ProductResponse> getAllTestProducts() {
        List<TestProduct> products = testproductRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(TestProduct testproduct) {
        return ProductResponse.builder()
                .id(testproduct.getId())
                .name(testproduct.getName())
                .description(testproduct.getDescription())
                .price(testproduct.getPrice())
                .build();
    }
}
