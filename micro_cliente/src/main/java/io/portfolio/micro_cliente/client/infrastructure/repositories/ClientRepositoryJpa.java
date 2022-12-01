package io.portfolio.micro_cliente.client.infrastructure.repositories;

import io.portfolio.micro_cliente.client.domain.entities.ClientEntityImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepositoryJpa extends JpaRepository<ClientEntityImpl, Long> {

    Optional<ClientEntityImpl> findByCpf(String cpf);
    void deleteByCpf(String cpf);
}
