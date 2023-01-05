package io.portfolio.micro_cliente.client.infrastructure.repositories.client;

import io.portfolio.micro_cliente.client.domain.entities.client.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepositoryJpa extends JpaRepository<AddressEntity, Long> { }
