package com.sp.library.interceptor;

import com.sp.library.jpaconfig.TenantContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

public class TenantInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TenantInterceptor.class);

    private static final String TENANT_ID = "Tenant-Id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        final var xTenantId = request.getHeader(TENANT_ID);
        if (xTenantId == null) {
            return respondMissingTenant(response);
        }
        return selectTenantAndContinue(xTenantId);
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) {
        TenantContext.clear();
        LOGGER.debug("Removed tenant assigned previously before sending response to client");
    }

    private boolean respondMissingTenant(HttpServletResponse response) throws IOException {
        final var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Missing database tenant");
        problemDetail.setDetail("Header X-Tenant-Id was not present in the request");

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.getWriter().print(problemDetail);
        return false;
    }

    private boolean respondUnknownTenant(String xTenantId, HttpServletResponse response) throws IOException {
        final var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Unknown database tenant");
        problemDetail.setDetail("Value of header X-Tenant-Id does not match a known database tenant");
        problemDetail.setProperty("tenantId", xTenantId);

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.getWriter().print(problemDetail);
        return false;
    }

    private boolean selectTenantAndContinue(String xTenantId) {
        TenantContext.setTenant(xTenantId);
        LOGGER.debug("Handling request for tenant {}", xTenantId);
        return true;
    }
}
