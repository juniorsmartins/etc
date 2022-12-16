package io.portfolio.micro_cliente.client.infrastructure.repositories.client;

import io.portfolio.micro_cliente.client.domain.client.ClientCompanyEntity;
import io.portfolio.micro_cliente.client.domain.ports.PolicyClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientCompanyRepositoryImpl implements PolicyClientRepository<ClientCompanyEntity, Long> {

    private static Logger log = LoggerFactory.getLogger(ClientCompanyRepositoryImpl.class);

    @Autowired
    private ClientCompanyRepositoryJpa repositoryJpa;

    @Override
    public ClientCompanyEntity saveEntity(ClientCompanyEntity entity) {

        log.info("Started resource fetch repository.");
        var response = this.repositoryJpa.saveAndFlush(entity);
        log.info("Completed resource record repository.");

        return response;
    }

    @Override
    public Optional<ClientCompanyEntity> searchById(Long id) {

        log.info("Started search by id in repository.");
        var response = this.repositoryJpa.findById(id);
        log.info("Completed search by id in repository.");

        return response;
    }

    @Override
    public Optional<ClientCompanyEntity> searchByDocument(String cnpj) {

        log.info("Started search by document in repository.");
        var response = this.repositoryJpa.findByCnpj(cnpj);
        log.info("Completed search by document in repository.");

        return response;
    }

    @Override
    public Page<ClientCompanyEntity> searchAll(Example filter, Pageable pagination) {

        log.info("Started search all in repository.");
        var response = this.repositoryJpa.findAll(filter, pagination);
        log.info("Completed search all in repository.");

        return response;
    }

    @Override
    public void deleteById(Long id) {

        log.info("Started delete by id in repository.");
        this.repositoryJpa.deleteById(id);
        log.info("Completed delete by id in repository.");
    }
}
