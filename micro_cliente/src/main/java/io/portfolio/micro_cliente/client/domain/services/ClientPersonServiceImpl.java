package io.portfolio.micro_cliente.client.domain.services;

import io.portfolio.micro_cliente.client.domain.client.ClientPersonEntityImpl;
import io.portfolio.micro_cliente.client.domain.dtos.ClientPersonDTORequestImpl;
import io.portfolio.micro_cliente.client.domain.dtos.ClientPersonDTOResponseImpl;
import io.portfolio.micro_cliente.client.domain.filter.ClientPersonFilterImpl;
import io.portfolio.micro_cliente.client.domain.ports.PolicyRepository;
import io.portfolio.micro_cliente.shared.exceptions.BusinessRuleViolationCustomException;
import io.portfolio.micro_cliente.shared.exceptions.ResourceNotFoundCustomException;
import io.portfolio.micro_cliente.shared.messages.MessagesProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.Optional;

@Service
public non-sealed class ClientPersonServiceImpl implements PolicyService<ClientPersonDTORequestImpl, ClientPersonFilterImpl,
        ClientPersonDTOResponseImpl, ClientPersonEntityImpl, Long> {

    @Autowired
    private PolicyRepository<ClientPersonEntityImpl, Long> repository;

    @Autowired
    private MessagesProperties messages;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<ClientPersonDTOResponseImpl> create(ClientPersonDTORequestImpl dto) {
        return Optional.of(dto)
                .map(ClientPersonEntityImpl::new)
                .map(client -> {
                    validateUniqueCPFRule(client.getCpf());
                    return this.repository.create(client);
                })
                .map(ClientPersonDTOResponseImpl::new)
                .map(dtoResponse -> ResponseEntity
                        .created(URI.create("/" + dtoResponse.id()))
                        .body(dtoResponse))
                .orElseThrow();
    }

        private void validateUniqueCPFRule(String cpf) {
            if(!this.repository.searchByDocument(cpf).isEmpty())
                throw new BusinessRuleViolationCustomException(messages.getSingleCpfRuleViolation());
        }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<ClientPersonDTOResponseImpl> update(ClientPersonDTORequestImpl dto) {
        validateUniqueCPFRuleByUpdate(dto);

        return this.repository.searchById(dto.id())
                .map(client -> {
                    client.setFirstName(dto.firstName());
                    client.setLastName(dto.lastName());
                    client.setCpf(dto.cpf());
                    client.setSex(dto.sex());
                    client.setGenre(dto.genre());
                    client.setBirthDate(dto.birthDate());
                    client.setMaritalStatus(dto.maritalStatus());
                    client.setEducation(dto.education());
                    return client;})
                .map(client -> ResponseEntity
                        .ok()
                        .body(new ClientPersonDTOResponseImpl(client))
                )
                .orElseThrow(() -> new ResourceNotFoundCustomException(messages.getResourceNotFound()));
    }

        private void validateUniqueCPFRuleByUpdate(ClientPersonDTORequestImpl dto) {
            var clientByCPF = this.repository.searchByDocument(dto.cpf());
            if(!clientByCPF.isEmpty() && clientByCPF.get().getId() != dto.id()) {
                throw new BusinessRuleViolationCustomException(messages.getSingleCpfRuleViolation());
            }
        }

    @Override
    public ResponseEntity<ClientPersonDTOResponseImpl> searchById(Long id) {
        return this.repository.searchById(id)
                .map(client -> ResponseEntity
                        .ok()
                        .body(new ClientPersonDTOResponseImpl(client))
                )
                .orElseThrow(() -> new ResourceNotFoundCustomException(messages.getResourceNotFound()));
    }

    @Override
    public ResponseEntity<Page<ClientPersonDTOResponseImpl>> searchAll(ClientPersonFilterImpl filter, Pageable pagination) {
        return ResponseEntity
                .ok()
                .body(this.repository.searchAll(configureFilter(filter), pagination)
                        .map(ClientPersonDTOResponseImpl::new));
    }

        private Example<ClientPersonEntityImpl> configureFilter(ClientPersonFilterImpl filter) {
            // ExampleMatcher - permite configurar condições para serem aplicadas nos filtros
            ExampleMatcher exampleMatcher = ExampleMatcher
                    .matchingAll()
                    .withIgnoreCase() // Ignorar caixa alta ou baixa - quando String
                    .withIgnoreNullValues() // Ignorar valores nulos
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // permite encontrar palavras parecidas - tipo Like do SQL

            // Example - pega campos populados para criar filtros
            return Example.of(ClientPersonEntityImpl.builder()
                        .firstName(filter.firstName())
                        .lastName(filter.lastName())
                        .cpf(filter.cpf())
                        .sex(filter.sex())
                        .genre(filter.genre())
                        .maritalStatus(filter.maritalStatus())
                        .education(filter.education())
                        .build(), exampleMatcher);
        }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<?> deleteById(Long id) {
        return this.repository.searchById(id)
                .map(client -> {
                    this.repository.deleteById(client.getId());
                    return ResponseEntity
                            .ok()
                            .body(messages.getResourceDeletedSuccessfully());
                })
                .orElseThrow(() -> new ResourceNotFoundCustomException(messages.getResourceNotFound()));
    }
}
