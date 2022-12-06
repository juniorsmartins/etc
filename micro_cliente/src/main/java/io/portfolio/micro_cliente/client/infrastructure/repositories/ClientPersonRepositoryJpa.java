package io.portfolio.micro_cliente.client.infrastructure.repositories;

import io.portfolio.micro_cliente.client.domain.client.ClientPersonEntityImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientPersonRepositoryJpa extends JpaRepository<ClientPersonEntityImpl, Long> {

    Optional<ClientPersonEntityImpl> findByCpf(String cpf);
}
