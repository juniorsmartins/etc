package io.portfolio.micro_cliente.client.domain.services.security.user;

import io.portfolio.micro_cliente.client.domain.entities.user.UserEntity;
import io.portfolio.micro_cliente.client.domain.ports.user.PolicyUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public non-sealed class AuthenticationService implements PolicyUserService, UserDetailsService {

    private static Logger log = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private PolicyUserRepository<UserEntity, Long> userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByLogin(username);
    }
}
