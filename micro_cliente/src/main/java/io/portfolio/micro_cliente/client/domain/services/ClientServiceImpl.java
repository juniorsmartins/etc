package io.portfolio.micro_cliente.client.domain.services;

import io.portfolio.micro_cliente.client.domain.dtos.ClientDTORequestImpl;
import io.portfolio.micro_cliente.client.domain.dtos.ClientDTOResponseImpl;
import io.portfolio.micro_cliente.client.domain.dtos.PolicyDTO;
import io.portfolio.micro_cliente.client.domain.entities.ClientEntity;
import io.portfolio.micro_cliente.client.domain.filter.ClientFilterImpl;
import io.portfolio.micro_cliente.client.domain.filter.PolicyFilter;
import io.portfolio.micro_cliente.client.infrastructure.repositories.ClientRepository;
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

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<ClientDTOResponseImpl> create(ClientDTORequestImpl dto) {
        return Optional.of(dto)
                .map(ClientEntity::new)
                .map(client -> {
                    if(!this.repository.findByCpf(client.getCpf()).isEmpty())
                        throw new NullPointerException();

                    return this.repository.saveAndFlush(client);
                })
                .map(ClientDTOResponseImpl::new)
                .map(dtoResponse -> ResponseEntity
                        .created(URI.create("/" + dtoResponse.getId()))
                        .body(dtoResponse))
                .orElseThrow();
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
