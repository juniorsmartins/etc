package io.portfolio.micro_cliente.client.domain.services.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        log.info("Start - configurar padrão de security");
        var defaultSecurityFilterChain = httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(this.securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
        log.info("Return - configuração de security padronizada");

        return defaultSecurityFilterChain;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        AuthenticationManager authenticationManager = null;

        try {
            log.info("Start - criar authenticationManager");
            authenticationManager = authenticationConfiguration.getAuthenticationManager();

        } catch(Exception e) {
            throw new RuntimeException("Error creating AuthenticationManager");
        }

        log.info("Return - authenticationManager criado");
        return authenticationManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        log.info("Start - criar codificador de senha");
        var passwordEncoder = new BCryptPasswordEncoder();
        log.info("Return - codificador criado");

        return passwordEncoder;
    }
}

