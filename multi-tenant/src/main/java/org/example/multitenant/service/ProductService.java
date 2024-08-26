package org.example.multitenant.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.multitenant.dto.ProductRequest;
import org.example.multitenant.dto.ProductResponse;
import org.example.multitenant.model.product.Product;
import org.example.multitenant.model.testproduct.TestProduct;
import org.example.multitenant.model.user.User;
import org.example.multitenant.repository.dao.product.ProductRepository;
import org.example.multitenant.repository.dao.testproduct.TestProductRepository;
import org.example.multitenant.repository.dao.user.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final TestProductRepository testproductRepository;
    private final UserRepository userRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    public List<ProductResponse> getAllProductsAndTestProduct() {
        List<Product> products = productRepository.findAll();
        List<TestProduct> testproducts = testproductRepository.findAll();
        List<User> users = userRepository.findAll();
        String usersInfo = users.stream()
                .map(User::toString)
                .collect(Collectors.joining(", "));
        log.info("usersInfo {} is saved", usersInfo);

        List<ProductResponse> productResponses = products.stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());

        List<ProductResponse> testProductResponses = testproducts.stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());

        // Kết hợp hai danh sách thành một
        productResponses.addAll(testProductResponses);

        return productResponses;
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
    private ProductResponse mapToProductResponse(TestProduct product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
