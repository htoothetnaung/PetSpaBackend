package com.spaService.ServiceBookingSystem.configs;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

    private static final String ALLOWED_ORIGINS = "https://petspafrontend-production.up.railway.app"; // Replace with your frontend's URL
    private static final String ALLOWED_METHODS = "POST, GET, PUT, OPTIONS, DELETE";
    private static final String ALLOWED_HEADERS = "Authorization, Content-Type, Accept, X-Requested-With";
    private static final int MAX_AGE = 3600;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        String originHeader = request.getHeader("Origin");

        // Check if the origin is allowed
        if (originHeader != null && originHeader.equals(ALLOWED_ORIGINS)) {
            response.setHeader("Access-Control-Allow-Origin", originHeader);
            response.setHeader("Access-Control-Allow-Methods", ALLOWED_METHODS);
            response.setHeader("Access-Control-Max-Age", String.valueOf(MAX_AGE));
            response.setHeader("Access-Control-Allow-Headers", ALLOWED_HEADERS);
            response.setHeader("Access-Control-Allow-Credentials", "true");
        }

        // Handle preflight requests
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return; // Terminate the request flow
        }

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // Initialization logic (if needed)
    }

    @Override
    public void destroy() {
        // Cleanup logic (if needed)
    }
}