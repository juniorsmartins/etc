package io.portfolio.micro_cliente.client.infrastructure.repositories.client;

import io.portfolio.micro_cliente.client.domain.entities.client.ClientPersonEntity;
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
public class ClientPersonRepositoryImpl implements PolicyClientRepository<ClientPersonEntity, Long> {

    private static Logger log = LoggerFactory.getLogger(ClientPersonRepositoryImpl.class);

    @Autowired
    private ClientPersonRepositoryJpa repositoryJpa;

    @Override
    public ClientPersonEntity saveEntity(ClientPersonEntity entity) {
        log.info("Started resource fetch repository.");

        var response = repositoryJpa.saveAndFlush(entity);

        log.info("Completed resource record repository.");
        return response;
    }

    @Override
    public Optional<ClientPersonEntity> searchById(Long id) {
        log.info("Started search by id in repository.");

        var response = this.repositoryJpa.findById(id);

        log.info("Completed search by id in repository.");
        return response;
    }

    @Override
    public Optional<ClientPersonEntity> searchByDocument(String cpf) {
        log.info("Started search by document in repository.");

        var response = this.repositoryJpa.findByCpf(cpf);

        log.info("Completed search by document in repository.");
        return response;
    }

    @Override
    public Page<ClientPersonEntity> searchAll(Example filter, Pageable pagination) {
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
