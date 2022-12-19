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

        log.info("Start - configuração de security");
                // Desabilita contra ataques CSRF - Cross-Site Request Forgery (o JWT já faz. Deixar habilitado seria redundante)
        var response = httpSecurity.csrf().disable()
                // Define política de gerenciamento de sessão (não exibe tela padrão de login e nem bloqueia URLs automático)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(this.securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
        log.info("Return - security configurado");

        return response;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        log.info("Start - construção de AuthenticationManager");
        var response = authenticationConfiguration.getAuthenticationManager();
        log.info("Return - disponibilização de AuthenticationManager");

        return response;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        log.info("Start - codificação de senha");
        var response = new BCryptPasswordEncoder();
        log.info("Return - senha codificada");

        return response;
    }
}

