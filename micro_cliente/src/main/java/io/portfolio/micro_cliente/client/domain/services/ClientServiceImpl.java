package io.portfolio.micro_cliente.client.domain.services;

import io.portfolio.micro_cliente.client.domain.dtos.ClientDTORequestImpl;
import io.portfolio.micro_cliente.client.domain.dtos.ClientDTOResponseImpl;
import io.portfolio.micro_cliente.client.domain.entities.ClientEntityImpl;
import io.portfolio.micro_cliente.client.domain.filter.ClientFilterImpl;
import io.portfolio.micro_cliente.client.domain.ports.PolicyRepository;
import io.portfolio.micro_cliente.shared.exceptions.BusinessRuleViolationCustomException;
import io.portfolio.micro_cliente.shared.exceptions.ResourceNotFoundCustomException;
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
public non-sealed class ClientServiceImpl implements PolicyService<ClientDTORequestImpl, ClientFilterImpl, ClientDTOResponseImpl, ClientEntityImpl, Long> {

    @Autowired
    private PolicyRepository<ClientEntityImpl, ClientFilterImpl, Long> repository;

    @Autowired
    private MessagesProperties messages;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public ResponseEntity<ClientDTOResponseImpl> create(ClientDTORequestImpl dto) {
        return Optional.of(dto)
                .map(ClientEntityImpl::new)
                .map(client -> {
                    validateUniqueCPFRule(client.getCpf());
                    return this.repository.create(client);
                })
                .map(ClientDTOResponseImpl::new)
                .map(dtoResponse -> ResponseEntity
                        .created(URI.create("/" + dtoResponse.id()))
                        .body(dtoResponse))
                .orElseThrow();
    }

        private void validateUniqueCPFRule(String cpf) {
            if(!this.repository.searchByCpf(cpf).isEmpty())
                throw new BusinessRuleViolationCustomException(messages.getSingleCpfRuleViolation());
        }


    @Override
    public ResponseEntity<ClientDTOResponseImpl> update(ClientDTORequestImpl dto) {
        validateUniqueCPFRuleByUpdate(dto);

        return this.repository.searchById(dto.getId())
                .map(client -> {
                    client.setFirstName(dto.getFirstName());
                    client.setLastName(dto.getLastName());
                    client.setCpf(dto.getCpf());
                    client.setSex(dto.getSex());
                    client.setGenre(dto.getGenre());
                    client.setBirthDate(dto.getBirthDate());
                    client.setMaritalStatus(dto.getMaritalStatus());
                    client.setEducation(dto.getEducation());
                    return client;})
                .map(client -> ResponseEntity
                        .ok()
                        .body(new ClientDTOResponseImpl(client))
                )
                .orElseThrow(() -> new ResourceNotFoundCustomException(messages.getResourceNotFound()));
    }

        private void validateUniqueCPFRuleByUpdate(ClientDTORequestImpl dto) {
            var clientByCPF = this.repository.searchByCpf(dto.getCpf());
            if(!clientByCPF.isEmpty() && clientByCPF.get().getId() != dto.getId()) {
                throw new BusinessRuleViolationCustomException(messages.getSingleCpfRuleViolation());
            }
        }



    @Override
    public ResponseEntity<ClientDTOResponseImpl> searchById(Long id) {
        return this.repository.searchById(id)
                .map(client -> ResponseEntity
                        .ok()
                        .body(new ClientDTOResponseImpl(client))
                )
                .orElseThrow(() -> new ResourceNotFoundCustomException(messages.getResourceNotFound()));
    }

    @Override
    public ResponseEntity<Page<ClientDTOResponseImpl>> searchAll(ClientFilterImpl filter, Pageable pagination) {
        return null;
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
