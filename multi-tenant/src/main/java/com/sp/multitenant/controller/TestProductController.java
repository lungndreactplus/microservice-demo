package com.sp.multitenant.controller;

import com.sp.multitenant.dto.ProductRequest;
import com.sp.multitenant.dto.ProductResponse;
import com.sp.multitenant.service.TestProductService;
import lombok.RequiredArgsConstructor;
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
