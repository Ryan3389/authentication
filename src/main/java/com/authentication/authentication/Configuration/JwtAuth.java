package com.authentication.authentication.Configuration;

import com.authentication.authentication.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;


@Component
//We want this to run once every request
public class JwtAuth extends OncePerRequestFilter {

    // Dependencies
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;


    // Constructor, initialize dependencies
    public JwtAuth(HandlerExceptionResolver handlerExceptionResolver, UserDetailsService userDetailsService, JwtService jwtService) {
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull  HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        // Grab Authorization field in the header
        String authHeader = request.getHeader("Authorization");

        // Check if header is null or if there is Bearer in headers
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // Grab the token from the header
            String token = authHeader.substring(7);

            // Extract the email from the token
            String userEmail = jwtService.extractUsername(token);

            // Get the current Authentication object, which may hold user details if already authenticated
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // If user email is present and no user is currently authenticated
            if (userEmail != null && authentication == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

                // By this point we have the user and token, next step is to ensure the token is valid
                if (jwtService.validateToken(token, userDetails)) {
                    // If token is valid, create an Authentication object to securely store user details
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // Store the Authentication object in the SecurityContext to mark the user as authenticated
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }


    }
}
