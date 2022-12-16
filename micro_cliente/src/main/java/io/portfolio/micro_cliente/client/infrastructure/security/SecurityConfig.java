package io.portfolio.micro_cliente.client.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                // Desabilita contra ataques CSRF - Cross-Site Request Forgery (o JWT já faz. Deixar habilitado seria redundante)
        return httpSecurity.csrf().disable()
                // Define política de gerenciamento de sessão (não exibe tela padrão de login e nem bloqueia URLs automático)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().build();
    }
}

