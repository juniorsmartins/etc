package io.portfolio.micro_cliente.client.infrastructure.repositories;

import io.portfolio.micro_cliente.client.domain.entities.ClientEntityImpl;
import io.portfolio.micro_cliente.client.domain.filter.ClientFilterImpl;
import io.portfolio.micro_cliente.client.domain.ports.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientRepositoryImpl implements PolicyRepository<ClientEntityImpl, ClientFilterImpl, Long> {

    @Autowired
    private ClientRepositoryJpa repositoryJpa;

    @Override
    public ClientEntityImpl create(ClientEntityImpl entity) {
        return repositoryJpa.saveAndFlush(entity);
    }

    @Override
    public Optional<ClientEntityImpl> search(ClientEntityImpl entity) {
        return null;
    }

    @Override
    public Optional<ClientEntityImpl> searchById(Long id) {
        return this.repositoryJpa.findById(id);
    }

    @Override
    public Optional<ClientEntityImpl> searchByCpf(String cpf) {
        return this.repositoryJpa.findByCpf(cpf);
    }

    @Override
    public Page<ClientEntityImpl> searchAll(ClientFilterImpl filter, Pageable pagination) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        this.repositoryJpa.deleteById(id);
    }

    @Override
    public void delete(ClientEntityImpl entity) {

    }
}
