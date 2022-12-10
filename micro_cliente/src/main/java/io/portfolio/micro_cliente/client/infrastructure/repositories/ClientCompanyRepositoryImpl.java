package io.portfolio.micro_cliente.client.infrastructure.repositories;

import io.portfolio.micro_cliente.client.domain.client.ClientCompanyEntity;
import io.portfolio.micro_cliente.client.domain.ports.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientCompanyRepositoryImpl implements PolicyRepository<ClientCompanyEntity, Long> {

    @Autowired
    private ClientCompanyRepositoryJpa repositoryJpa;

    @Override
    public ClientCompanyEntity saveEntity(ClientCompanyEntity entity) {
        return this.repositoryJpa.saveAndFlush(entity);
    }

    @Override
    public Optional<ClientCompanyEntity> searchById(Long id) {
        return this.repositoryJpa.findById(id);
    }

    @Override
    public Optional<ClientCompanyEntity> searchByDocument(String cnpj) {
        return this.repositoryJpa.findByCnpj(cnpj);
    }

    @Override
    public Page<ClientCompanyEntity> searchAll(Example filter, Pageable pagination) {
        return this.repositoryJpa.findAll(filter, pagination);
    }

    @Override
    public void deleteById(Long id) {
        this.repositoryJpa.deleteById(id);
    }
}
