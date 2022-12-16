package io.portfolio.micro_cliente.client.infrastructure.repositories.client;

import io.portfolio.micro_cliente.client.domain.client.ClientCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientCompanyRepositoryJpa extends JpaRepository<ClientCompanyEntity, Long> {

    Optional<ClientCompanyEntity> findByCnpj(String cnpj);
}
