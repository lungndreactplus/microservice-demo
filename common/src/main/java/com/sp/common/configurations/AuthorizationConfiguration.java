package com.sp.common.configurations;

import com.sp.common.executors.AuthorizationExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthorizationConfiguration extends GlobalMethodSecurityConfiguration {

    private final AuthorizationExecutor authorizationExecutor;

    public AuthorizationConfiguration(AuthorizationExecutor authorizationExecutor) {
        this.authorizationExecutor = authorizationExecutor;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(authorizationExecutor);
        return expressionHandler;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(x -> x.disable());
        http.authorizeHttpRequests(x -> x
                //.requestMatchers(HttpMethod.GET, "/orders").access(new CustomAuthorizationManager("Tenants", "Tenants:CRUD"))
                .anyRequest().authenticated());
        http.oauth2ResourceServer(x -> x.jwt(jwt -> Customizer.withDefaults()));
        http.sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
