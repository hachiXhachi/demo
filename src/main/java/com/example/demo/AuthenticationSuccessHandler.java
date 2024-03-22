package com.example.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        // Check if user is already authenticated
        if (request.getUserPrincipal() != null && !request.getUserPrincipal().getName().equals("anonymousUser")) {
            // Redirect to dashboard or admin page based on role
            if (isAdmin) {
                response.sendRedirect("/admin");
            } else {
                response.sendRedirect("/dashboard");
            }
        } else {
            // User is not authenticated, proceed with default behavior
            if (isAdmin) {
                setDefaultTargetUrl("/admin");
            } else {
                setDefaultTargetUrl("/dashboard");
            }
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
