package io.portfolio.micro_cliente.client.domain.ports;

import io.portfolio.micro_cliente.client.domain.entities.ClientEntity;
import io.portfolio.micro_cliente.client.domain.entities.PolicyEntity;
import io.portfolio.micro_cliente.client.domain.filter.PolicyFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PolicyRepository {
    Optional<PolicyEntity> create(PolicyEntity entity);
    Optional<PolicyEntity> searchById(Long id);
    Optional<PolicyEntity> search(PolicyEntity entity);
    Page<PolicyEntity> searchAll(PolicyFilter filter, Pageable pagination);
    void deleteById(Long id);
    void delete(PolicyEntity entity);
}
