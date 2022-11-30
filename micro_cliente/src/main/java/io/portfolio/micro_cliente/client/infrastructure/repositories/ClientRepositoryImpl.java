package io.portfolio.micro_cliente.client.infrastructure.repositories;

import io.portfolio.micro_cliente.client.domain.entities.ClientEntity;
import io.portfolio.micro_cliente.client.domain.filter.ClientFilterImpl;
import io.portfolio.micro_cliente.client.domain.ports.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientRepositoryImpl implements PolicyRepository<ClientEntity, ClientFilterImpl, Long> {

    @Autowired
    private ClientRepositoryJpa repositoryJpa;

    @Override
    public ClientEntity create(ClientEntity entity) {
        return repositoryJpa.saveAndFlush(entity);
    }

    @Override
    public Optional<ClientEntity> search(ClientEntity entity) {
        return null;
    }

    @Override
    public Optional<ClientEntity> searchById(Long id) {
        return this.repositoryJpa.findById(id);
    }

    @Override
    public Optional<ClientEntity> searchByCpf(String cpf) {
        return this.repositoryJpa.findByCpf(cpf);
    }

    @Override
    public Page<ClientEntity> searchAll(ClientFilterImpl filter, Pageable pagination) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        this.repositoryJpa.deleteById(id);
    }

    @Override
    public void delete(ClientEntity entity) {

    }
}
