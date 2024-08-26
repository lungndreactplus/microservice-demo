package org.example.productservice.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.multitenant.config.Tenants;
import org.example.multitenant.interceptor.TenantInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.Set;

@Configuration
public class ServiceConfiguration  {

    @Bean
    public WebMvcConfigurer webMvcConfigurer(ObjectMapper objectMapper, Tenants tenants) {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new TenantInterceptor(objectMapper, tenants)).addPathPatterns("/**");
            }
        };
    }
}
//@Configuration
//public class ServiceConfiguration implements WebMvcConfigurer {
//
//    private final ObjectMapper objectMapper;
//    private final Tenants tenants;
//
//    public ServiceConfiguration(ObjectMapper objectMapper, Tenants tenants) {
//        this.objectMapper = objectMapper;
//        this.tenants = tenants;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new TenantInterceptor(objectMapper, tenants));
//    }
//    @Bean
//    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
//        return new RequestMappingHandlerAdapter();
//    }
//}