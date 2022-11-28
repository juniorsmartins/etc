package io.portfolio.micro_cliente.client.infrastructure.repositories;

import io.portfolio.micro_cliente.client.domain.entities.ClientEntity;
import io.portfolio.micro_cliente.client.domain.entities.PolicyEntity;
import io.portfolio.micro_cliente.client.domain.filter.PolicyFilter;
import io.portfolio.micro_cliente.client.domain.ports.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class ClientRepositoryImpl implements PolicyRepository {

    @Autowired
    private ClientRepositoryJpa repositoryJpa;

    @Override
    public Optional<PolicyEntity> create(PolicyEntity entity) {
        return Optional.empty();
    }

    @Override
    public Optional<PolicyEntity> searchById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<PolicyEntity> search(PolicyEntity entity) {
        return Optional.empty();
    }

    @Override
    public Page<PolicyEntity> searchAll(PolicyFilter filter, Pageable pagination) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(PolicyEntity entity) {

    }
}
