package org.example.multitenant.controller;

import lombok.RequiredArgsConstructor;


import org.example.multitenant.dto.ProductRequest;
import org.example.multitenant.dto.ProductResponse;
import org.example.multitenant.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
//@RefreshScope
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasAuthority('ADMIN') and hasAuthority('SCOPE_create_product')")
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("productandtestproduct")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProductsAndTestProduct() {
        return productService.getAllProductsAndTestProduct();
    }

}
