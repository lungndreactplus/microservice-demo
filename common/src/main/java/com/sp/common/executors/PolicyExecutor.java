package com.sp.common.executors;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.keycloak.AuthorizationContext;
import org.keycloak.adapters.authorization.PolicyEnforcer;
import org.keycloak.adapters.authorization.integration.elytron.ServletHttpRequest;
import org.keycloak.adapters.authorization.integration.elytron.ServletHttpResponse;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RefreshScope
public class PolicyExecutor {

    @Value("${keycloak.url:null}")
    private String url;

    @Value("${keycloak.realm:null}")
    private String realm;

    @Value("${keycloak.service:null}")
    private String service;

    @Value("${keycloak.secret:null}")
    private String secret;

    private PolicyEnforcerConfig policyEnforcerConfig;
    private final Map<PolicyEnforcerConfig, PolicyEnforcer> policyEnforcer = Collections.synchronizedMap(new HashMap<>());

    public PolicyExecutor InitPolicyExecutor(){
        this.policyEnforcerConfig = setPolicyEnforcerConfig();
        return this;
    }

    public Boolean hasPermission(ServletRequest servletRequest, ServletResponse servletResponse, String resource, String scope, String path) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        var servletHttpRequest = new ServletHttpRequest(request, () -> extractBearerToken(request));
        var policyEnforcer = this.createPolicyEnforcer(resource, scope, path);
        var authzContext = policyEnforcer.enforce(servletHttpRequest, new ServletHttpResponse(response));
        request.setAttribute(AuthorizationContext.class.getName(), authzContext);
        return authzContext.hasPermission(resource, scope);
    }

    private String extractBearerToken(HttpServletRequest request) {
        Enumeration<String> authorizationHeaderValues = request.getHeaders("Authorization");
        while (authorizationHeaderValues.hasMoreElements()) {
            String value = authorizationHeaderValues.nextElement();
            String[] parts = value.trim().split("\\s+");
            if (parts.length == 2) {
                String bearer = parts[0];
                if (bearer.equalsIgnoreCase("Bearer")) {
                    return parts[1];
                }
            }
        }
        return null;
    }

    private org.keycloak.adapters.authorization.PolicyEnforcer createPolicyEnforcer(String resource, String scope, String path) {
        return this.policyEnforcer.computeIfAbsent(policyEnforcerConfig, enforcerConfig -> {
            setPathConfig(resource, scope, path);
            String authServerUrl = enforcerConfig.getAuthServerUrl();
            return PolicyEnforcer.builder().authServerUrl(authServerUrl).realm(enforcerConfig.getRealm()).clientId(enforcerConfig.getResource()).credentials(enforcerConfig.getCredentials()).bearerOnly(false).enforcerConfig(enforcerConfig).build();
        });
    }

    private PolicyEnforcerConfig setPolicyEnforcerConfig() {
        policyEnforcerConfig = new PolicyEnforcerConfig();
        policyEnforcerConfig.setAuthServerUrl(this.url);
        policyEnforcerConfig.setRealm(this.realm);
        policyEnforcerConfig.setResource(this.service);
        var credentials = new HashMap<String, Object>();
        credentials.put("secret", this.secret);
        policyEnforcerConfig.setCredentials(credentials);
        return policyEnforcerConfig;
    }

    private void setPathConfig(String resource, String scope, String path) {
        if (path == null)
            path = "/*";
        var paths = new ArrayList<PolicyEnforcerConfig.PathConfig>();
        var pathConfig = new PolicyEnforcerConfig.PathConfig();
        pathConfig.setName(resource);
        var scopes = new ArrayList<String>();
        scopes.add(scope);
        pathConfig.setScopes(scopes);
        pathConfig.setPath(path);
        paths.add(pathConfig);
        policyEnforcerConfig.setPaths(paths);
    }
}
