package io.portfolio.micro_cliente.client.infrastructure.repositories;

import io.portfolio.micro_cliente.client.domain.client.ClientCompanyEntityImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientCompanyRepositoryJpa extends JpaRepository<ClientCompanyEntityImpl, Long> {

    Optional<ClientCompanyEntityImpl> findByCnpj(String cnpj);
}
