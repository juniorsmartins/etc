package io.portfolio.micro_cliente.client.infrastructure.repositories;

import io.portfolio.micro_cliente.client.domain.client.ClientPersonImpl;
import io.portfolio.micro_cliente.client.domain.ports.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientPersonRepositoryImpl implements PolicyRepository<ClientPersonImpl, Long> {

    @Autowired
    private ClientPersonRepositoryJpa repositoryJpa;

    @Override
    public ClientPersonImpl create(ClientPersonImpl entity) {
        return repositoryJpa.saveAndFlush(entity);
    }

    @Override
    public Optional<ClientPersonImpl> searchById(Long id) {
        return this.repositoryJpa.findById(id);
    }

    @Override
    public Optional<ClientPersonImpl> searchByDocument(String cpf) {
        return this.repositoryJpa.findByCpf(cpf);
    }

    @Override
    public Page<ClientPersonImpl> searchAll(Example filter, Pageable pagination) {
        return this.repositoryJpa.findAll(filter, pagination);
    }

    @Override
    public void deleteById(Long id) {
        this.repositoryJpa.deleteById(id);
    }
}
