package io.portfolio.micro_cliente.client.domain.services.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var tokenJWT = recoverToken(request);
        System.out.println(tokenJWT);

        filterChain.doFilter(request, response); // chama o próximo filtro
    }

    private String recoverToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader == null)
            throw new RuntimeException("JWT Token not sent in authorization header.");

        return authorizationHeader.replace("Bearer", "");
    }
}

