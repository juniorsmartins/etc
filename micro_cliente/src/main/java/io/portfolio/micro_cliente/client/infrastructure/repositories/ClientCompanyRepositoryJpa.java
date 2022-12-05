package io.portfolio.micro_cliente.client.infrastructure.repositories;

import io.portfolio.micro_cliente.client.domain.client.ClientCompanyImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientCompanyRepositoryJpa extends JpaRepository<ClientCompanyImpl, Long> {

    Optional<ClientCompanyImpl> findByCnpj(String cnpj);
}
