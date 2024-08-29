package com.sp.productservice.controller;

import com.sp.productservice.dto.ProductRequest;
import com.sp.productservice.dto.ProductResponse;
import com.sp.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
//@RefreshScope
public class ProductController {

    private final ProductService productService;
//    @Value("${datasource.tenants.names:default unknown}")
    @Value("${message:default message}")
    private String message;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasAuthority('ADMIN') and hasAuthority('SCOPE_create_product')")
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN') and hasAuthority('SCOPE_view_product')")
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/message")
    public String getMessage() {
        return message;
    }


}
