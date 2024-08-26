package org.example.multitenant.controller;

import lombok.RequiredArgsConstructor;
import org.example.multitenant.dto.ProductRequest;
import org.example.multitenant.dto.ProductResponse;
import org.example.multitenant.service.TestProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testproduct")
@RequiredArgsConstructor
public class TestProductController {

    private final TestProductService testproductService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        testproductService.createTestProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return testproductService.getAllTestProducts();
    }

}
