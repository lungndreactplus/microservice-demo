package com.sp.apigateway.config;//package com.sp.apigateway.config;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class GatewayConfig {
//
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("product-service", r -> r.path("/api/product")
//                        .filters(f -> f.rewritePath("/product/(?<segment>.*)", "/${segment}"))
//                        .uri("lb://product-service"))
//                .route("order-service", r -> r.path("/api//order")
//                        .filters(f -> f.rewritePath("/order/(?<segment>.*)", "/${segment}"))
//                        .uri("lb://order-service"))
//                .build();
//    }
//}
