package io.portfolio.micro_cliente.client.infrastructure.repositories;

import io.portfolio.micro_cliente.client.domain.entities.ClientEntity;
import io.portfolio.micro_cliente.client.domain.ports.PolicyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepositoryJpa extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByCpf(String cpf);
    void deleteByCpf(String cpf);
}
