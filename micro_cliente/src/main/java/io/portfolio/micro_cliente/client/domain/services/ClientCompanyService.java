package io.portfolio.micro_cliente.client.domain.services;

import io.portfolio.micro_cliente.client.domain.client.ClientCompanyEntity;
import io.portfolio.micro_cliente.client.domain.dtos.ClientCompanyDTORequest;
import io.portfolio.micro_cliente.client.domain.dtos.ClientCompanyDTOResponse;
import io.portfolio.micro_cliente.client.domain.filter.ClientCompanyFilter;
import io.portfolio.micro_cliente.client.domain.ports.PolicyRepository;
import io.portfolio.micro_cliente.shared.exceptions.BusinessRuleViolationCustomException;
import io.portfolio.micro_cliente.shared.exceptions.InternalErrorCustomException;
import io.portfolio.micro_cliente.shared.exceptions.ResourceNotFoundCustomException;
import io.portfolio.micro_cliente.shared.messages.MessagesProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public non-sealed class ClientCompanyService implements PolicyService<ClientCompanyDTORequest,
        ClientCompanyFilter, ClientCompanyDTOResponse, ClientCompanyEntity, Long> {

    @Autowired
    private PolicyRepository<ClientCompanyEntity, Long> repository;

    @Autowired
    private MessagesProperties messages;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public ClientCompanyDTOResponse create(ClientCompanyDTORequest dto) {
        log.info("Create - started resource record service.");

        return Optional.of(dto)
                .map(ClientCompanyEntity::new)
                .map(clientNew -> {
                    validateUniqueCNPJRule(clientNew.getCnpj());
                    clientNew.getAddress().setClient(clientNew);
                    clientNew.getContact().setClient(clientNew);
                    return this.repository.saveEntity(clientNew);})
                .map(ClientCompanyDTOResponse::new)
                .orElseThrow(() -> {
                    log.error("Exception - internal server error.");
                    throw new InternalErrorCustomException(messages.getUnavailableServer());
                });
    }

        private void validateUniqueCNPJRule(String cnpj) {
            if(!this.repository.searchByDocument(cnpj).isEmpty())
                throw new BusinessRuleViolationCustomException(messages.getSingleCnpjRuleViolation());
        }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public ClientCompanyDTOResponse update(ClientCompanyDTORequest dto) {

        return this.repository.searchById(dto.id())
                .map(client -> {
                    validateUniqueCNPJRuleByUpdate(dto);

                    client.setBusinessName(dto.businessName());
                    client.setFantasyName(dto.fantasyName());
                    client.setCnpj(dto.cnpj());
                    client.setBirthDate(dto.birthDate());

                    client.getAddress().setCep(dto.address().cep());
                    client.getAddress().setState(dto.address().state());
                    client.getAddress().setCity(dto.address().city());
                    client.getAddress().setDistrict(dto.address().district());
                    client.getAddress().setPublicPlace(dto.address().publicPlace());
                    client.getAddress().setHouseNumber(dto.address().houseNumber());
                    client.getAddress().setComplement(dto.address().complement());

                    client.getContact().setEmail(dto.contact().email());
                    client.getContact().setCell(dto.contact().cell());

                    return client;})
                .map(client -> new ClientCompanyDTOResponse(client))
                .orElseThrow(() -> new ResourceNotFoundCustomException(messages.getResourceNotFound()));
    }

        private void validateUniqueCNPJRuleByUpdate(ClientCompanyDTORequest dto) {
            var clientByCNPJ = this.repository.searchByDocument(dto.cnpj());
            if(!clientByCNPJ.isEmpty() && clientByCNPJ.get().getId() != dto.id()) {
                throw new BusinessRuleViolationCustomException(messages.getSingleCnpjRuleViolation());
            }
        }

    @Override
    public ClientCompanyDTOResponse searchById(Long id) {
        return this.repository.searchById(id)
                .map(client -> new ClientCompanyDTOResponse(client))
                .orElseThrow(() -> new ResourceNotFoundCustomException(messages.getResourceNotFound()));
    }

    @Override
    public Page<ClientCompanyDTOResponse> searchAll(ClientCompanyFilter filter, Pageable pagination) {
        return this.repository.searchAll(configureFilter(filter), pagination)
                .map(ClientCompanyDTOResponse::new);
    }

        private Example<ClientCompanyEntity> configureFilter(ClientCompanyFilter filter) {
            // ExampleMatcher - permite configurar condições para serem aplicadas nos filtros
            ExampleMatcher exampleMatcher = ExampleMatcher
                    .matchingAll()
                    .withIgnoreCase() // Ignorar caixa alta ou baixa - quando String
                    .withIgnoreNullValues() // Ignorar valores nulos
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // permite encontrar palavras parecidas - tipo Like do SQL

            // Example - pega campos populados para criar filtros
            return Example.of(ClientCompanyEntity.builder()
                        .businessName(filter.businessName())
                        .fantasyName(filter.fantasyName())
                        .cnpj(filter.cnpj())
                        .build(), exampleMatcher);
        }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public String deleteById(Long id) {
        return this.repository.searchById(id)
                .map(client -> {
                    this.repository.deleteById(client.getId());
                    return messages.getResourceDeletedSuccessfully();})
                .orElseThrow(() -> new ResourceNotFoundCustomException(messages.getResourceNotFound()));
    }
}
