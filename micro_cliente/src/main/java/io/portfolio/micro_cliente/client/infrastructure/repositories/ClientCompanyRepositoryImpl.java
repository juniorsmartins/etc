package io.portfolio.micro_cliente.client.infrastructure.repositories;

import io.portfolio.micro_cliente.client.domain.client.ClientCompanyEntityImpl;
import io.portfolio.micro_cliente.client.domain.ports.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientCompanyRepositoryImpl implements PolicyRepository<ClientCompanyEntityImpl, Long> {

    @Autowired
    private ClientCompanyRepositoryJpa repositoryJpa;

    @Override
    public ClientCompanyEntityImpl create(ClientCompanyEntityImpl entity) {
        return this.repositoryJpa.saveAndFlush(entity);
    }

    @Override
    public Optional<ClientCompanyEntityImpl> searchById(Long id) {
        return this.repositoryJpa.findById(id);
    }

    @Override
    public Optional<ClientCompanyEntityImpl> searchByDocument(String cnpj) {
        return this.repositoryJpa.findByCnpj(cnpj);
    }

    @Override
    public Page<ClientCompanyEntityImpl> searchAll(Example filter, Pageable pagination) {
        return this.repositoryJpa.findAll(filter, pagination);
    }

    @Override
    public void deleteById(Long id) {
        this.repositoryJpa.deleteById(id);
    }
}
