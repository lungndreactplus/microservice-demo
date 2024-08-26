package org.example.multitenant.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.multitenant.dto.ProductRequest;
import org.example.multitenant.dto.ProductResponse;
import org.example.multitenant.model.testproduct.TestProduct;
import org.example.multitenant.repository.dao.testproduct.TestProductRepository;
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
