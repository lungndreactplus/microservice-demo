package com.example.common.executors;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.Serializable;

@Component
public class AuthorizationExecutor implements PermissionEvaluator {

    private final PolicyExecutor policyExecutor;

    public AuthorizationExecutor(PolicyExecutor policyExecutor) {
        this.policyExecutor = policyExecutor.InitPolicyExecutor();
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if ((authentication == null) || (targetDomainObject == null) || !(permission instanceof String)) {
            return false;
        }
        var attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        var httpServletRequest = attributes.getRequest();
        var httpServletResponse = attributes.getResponse();
        var resource = targetDomainObject.toString();
        var scope = permission.toString();
        return policyExecutor.hasPermission(httpServletRequest, httpServletResponse, resource, scope, null);
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
        if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(auth, targetType.toUpperCase(), permission.toString().toUpperCase());
    }

    private boolean hasPrivilege(Authentication auth, String targetType, String permission) {
        for (GrantedAuthority grantedAuth : auth.getAuthorities()) {
            if (grantedAuth.getAuthority().startsWith(targetType) && grantedAuth.getAuthority().contains(permission)) {
                return true;
            }
        }
        return false;
    }
}
