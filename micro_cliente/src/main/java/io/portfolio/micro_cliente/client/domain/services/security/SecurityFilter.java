package io.portfolio.micro_cliente.client.domain.services.security;

import io.portfolio.micro_cliente.client.infrastructure.repositories.user.UserRepositoryJpa;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private static Logger log = LoggerFactory.getLogger(SecurityFilter.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepositoryJpa userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        log.info("Start - recuperar token");
        var tokenJWT = recoverToken(request);
        log.info("Medium - token -> " + tokenJWT);

        if(tokenJWT != null) {
            var subject = this.tokenService.getSubject(tokenJWT);
            var user = this.userRepository.findByLogin(subject);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("Medium - token autenticado");
        }

        log.info("Return - próximo filtro");
        filterChain.doFilter(request, response); // chama o próximo filtro
    }

    private String recoverToken(HttpServletRequest request) {

        log.info("Start - procurar token no header da requisição");
        var authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader != null) {
            var retrievedTokenWithoutPrefix = authorizationHeader.replace("Bearer ", "");
            log.info("Return - token encontrado e retornado sem prefixo");
            return retrievedTokenWithoutPrefix;
        }

        log.info("Return - token não encontrado - header vazio");
        return null;
    }
}

