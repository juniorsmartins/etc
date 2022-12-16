package io.portfolio.micro_cliente.client.infrastructure.repositories.user;

import io.portfolio.micro_cliente.client.domain.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {

    UserDetails findByLogin(String login);
}
