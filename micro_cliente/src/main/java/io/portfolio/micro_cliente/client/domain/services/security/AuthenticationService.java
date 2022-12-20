package io.portfolio.micro_cliente.client.domain.services.security;

import io.portfolio.micro_cliente.client.infrastructure.repositories.user.UserRepositoryJpa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {
    private static Logger log = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private UserRepositoryJpa userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("Start - busca por emailLogin no database");
        var userDetails = this.userRepository.findByLogin(username);
        log.info("Return - retorno de busca por emailLogin no database");

        return userDetails;
    }
}
