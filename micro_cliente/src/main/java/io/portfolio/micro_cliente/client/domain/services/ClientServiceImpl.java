package io.portfolio.micro_cliente.client.domain.services;

import io.portfolio.micro_cliente.client.domain.dtos.ClientDTORequestImpl;
import io.portfolio.micro_cliente.client.domain.dtos.ClientDTOResponseImpl;
import io.portfolio.micro_cliente.client.domain.entities.ClientEntity;
import io.portfolio.micro_cliente.client.domain.filter.ClientFilterImpl;
import io.portfolio.micro_cliente.client.infrastructure.repositories.ClientRepository;
import io.portfolio.micro_cliente.shared.exceptions.BusinessRuleViolationCustomException;
import io.portfolio.micro_cliente.shared.messages.MessagesProperties;
import org.springframework.beans.factory.annotation.Autowired;
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
public non-sealed class ClientServiceImpl implements PolicyService<ClientDTORequestImpl, ClientFilterImpl, ClientDTOResponseImpl, ClientEntity, Long> {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private MessagesProperties messagesProperties;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<ClientDTOResponseImpl> create(ClientDTORequestImpl dto) {
        return Optional.of(dto)
                .map(ClientEntity::new)
                .map(client -> {
                    validateUniqueCPFRule(client.getCpf());
                    return this.repository.saveAndFlush(client);
                })
                .map(ClientDTOResponseImpl::new)
                .map(dtoResponse -> ResponseEntity
                        .created(URI.create("/" + dtoResponse.getId()))
                        .body(dtoResponse))
                .orElseThrow();
    }

        private void validateUniqueCPFRule(String cpf) {
            if(!this.repository.findByCpf(cpf).isEmpty())
                throw new BusinessRuleViolationCustomException(messagesProperties.getBusinessRuleViolated());
        }

    @Override
    public ResponseEntity<ClientDTOResponseImpl> update(ClientDTORequestImpl dto) {
        return null;
    }

    @Override
    public ResponseEntity<ClientDTOResponseImpl> searchById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Page<ClientDTOResponseImpl>> searchAll(ClientFilterImpl filter, Pageable paginacao) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        return null;
    }
}
