package io.portfolio.micro_cliente.client.infrastructure.repositories.user;

import io.portfolio.micro_cliente.client.domain.ports.user.PolicyUserRepository;
import io.portfolio.micro_cliente.client.domain.entities.user.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements PolicyUserRepository<UserEntity, Long> {

    private static Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Autowired
    private UserRepositoryJpa userRepositoryJpa;

    @Override
    public UserEntity saveEntity(UserEntity entity) {
        return null;
    }

    @Override
    public UserDetails findByLogin(String username) {
        return this.userRepositoryJpa.findByLogin(username);
    }

    @Override
    public Optional<UserEntity> searchById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}
