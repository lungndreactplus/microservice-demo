package com.sp.multitenant.controller;

import com.sp.common.controller.BaseController;
import com.sp.multitenant.dto.ProductRequest;
import com.sp.multitenant.dto.ProductResponse;
import lombok.RequiredArgsConstructor;


import com.sp.multitenant.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@RefreshScope
public class ProductController extends BaseController {

    @Value("${message:null}")
    private String message;

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasAuthority('ADMIN') and hasAuthority('SCOPE_create_product')")
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<ProductResponse> getAllProducts() {
//        return productService.getAllProducts();
//    }

    @GetMapping("productandtestproduct")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasPermission('Tenants', 'Tenants:CRUD')")
    public List<ProductResponse> getAllProductsAndTestProduct() {
        return productService.getAllProductsAndTestProduct();
    }

    @GetMapping("message")
    @ResponseStatus(HttpStatus.OK)
    public String getUrl() {
        return message;
    }
}
