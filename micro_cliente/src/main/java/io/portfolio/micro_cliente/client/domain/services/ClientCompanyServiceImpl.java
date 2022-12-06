package io.portfolio.micro_cliente.client.domain.services;

import io.portfolio.micro_cliente.client.domain.client.ClientCompanyEntityImpl;
import io.portfolio.micro_cliente.client.domain.dtos.ClientCompanyDTORequestImpl;
import io.portfolio.micro_cliente.client.domain.dtos.ClientCompanyDTOResponseImpl;
import io.portfolio.micro_cliente.client.domain.filter.ClientCompanyFilterImpl;
import io.portfolio.micro_cliente.client.domain.ports.PolicyRepository;
import io.portfolio.micro_cliente.shared.exceptions.BusinessRuleViolationCustomException;
import io.portfolio.micro_cliente.shared.exceptions.ResourceNotFoundCustomException;
import io.portfolio.micro_cliente.shared.messages.MessagesProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.Optional;

@Service
public non-sealed class ClientCompanyServiceImpl implements PolicyService<ClientCompanyDTORequestImpl,
        ClientCompanyFilterImpl, ClientCompanyDTOResponseImpl, ClientCompanyEntityImpl, Long> {

    @Autowired
    private PolicyRepository<ClientCompanyEntityImpl, Long> repository;

    @Autowired
    private MessagesProperties messages;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<ClientCompanyDTOResponseImpl> create(ClientCompanyDTORequestImpl dto) {
        return Optional.of(dto)
                .map(ClientCompanyEntityImpl::new)
                .map(client -> {
                    validateUniqueCNPJRule(client.getCnpj());
                    return this.repository.create(client);
                })
                .map(ClientCompanyDTOResponseImpl::new)
                .map(dtoResponse -> ResponseEntity
                        .created(URI.create("/" + dtoResponse.id()))
                        .body(dtoResponse))
                .orElseThrow();
    }

        private void validateUniqueCNPJRule(String cnpj) {
            if(!this.repository.searchByDocument(cnpj).isEmpty())
                throw new BusinessRuleViolationCustomException(messages.getSingleCnpjRuleViolation());
        }

    @Override
    public ResponseEntity<ClientCompanyDTOResponseImpl> update(ClientCompanyDTORequestImpl dto) {
        return null;
    }

    @Override
    public ResponseEntity<ClientCompanyDTOResponseImpl> searchById(Long id) {
        return this.repository.searchById(id)
                .map(client -> ResponseEntity
                        .ok()
                        .body(new ClientCompanyDTOResponseImpl(client))
                )
                .orElseThrow(() -> new ResourceNotFoundCustomException(messages.getResourceNotFound()));
    }

    @Override
    public ResponseEntity<Page<ClientCompanyDTOResponseImpl>> searchAll(ClientCompanyFilterImpl filter, Pageable pagination) {
        return ResponseEntity
                .ok()
                .body(this.repository.searchAll(configureFilter(filter), pagination)
                        .map(ClientCompanyDTOResponseImpl::new));
    }

        private Example<ClientCompanyEntityImpl> configureFilter(ClientCompanyFilterImpl filter) {
            // ExampleMatcher - permite configurar condições para serem aplicadas nos filtros
            ExampleMatcher exampleMatcher = ExampleMatcher
                    .matchingAll()
                    .withIgnoreCase() // Ignorar caixa alta ou baixa - quando String
                    .withIgnoreNullValues() // Ignorar valores nulos
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // permite encontrar palavras parecidas - tipo Like do SQL

            // Example - pega campos populados para criar filtros
            return Example.of(ClientCompanyEntityImpl.builder()
                        .businessName(filter.businessName())
                        .fantasyName(filter.fantasyName())
                        .cnpj(filter.cnpj())
                        .build(), exampleMatcher);
        }

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
