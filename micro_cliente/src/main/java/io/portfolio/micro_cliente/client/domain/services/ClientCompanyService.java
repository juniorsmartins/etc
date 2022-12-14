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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public non-sealed class ClientCompanyService implements PolicyService<ClientCompanyDTORequest,
        ClientCompanyFilter, ClientCompanyDTOResponse, ClientCompanyEntity, Long> {

    private static Logger log = LoggerFactory.getLogger(ClientCompanyService.class);

    @Autowired
    private PolicyRepository<ClientCompanyEntity, Long> repository;

    @Autowired
    private MessagesProperties messages;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public ClientCompanyDTOResponse create(ClientCompanyDTORequest dto) {
        log.info("Started resource record service.");

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
            log.info("Single CNPJ rule validation.");

            if(this.repository.searchByDocument(cnpj).isPresent()) {
                log.error("Exception - business rule violation.");
                throw new BusinessRuleViolationCustomException(messages.getSingleCnpjRuleViolation());
            }
        }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public ClientCompanyDTOResponse update(ClientCompanyDTORequest dto) {
        log.info("Started resource update service.");

        return this.repository.searchById(dto.id())
                .map(client -> {
                    validateUniqueCNPJRuleByUpdate(dto);

                    client.setBusinessName(dto.businessName());
                    client.setFantasyName(dto.fantasyName());
                    client.setCnpj(dto.cnpj());
                    client.setBirthDate(dto.birthDate());
                    log.info("Updated company.");

                    client.getAddress().setCep(dto.address().cep());
                    client.getAddress().setState(dto.address().state());
                    client.getAddress().setCity(dto.address().city());
                    client.getAddress().setDistrict(dto.address().district());
                    client.getAddress().setPublicPlace(dto.address().publicPlace());
                    client.getAddress().setHouseNumber(dto.address().houseNumber());
                    client.getAddress().setComplement(dto.address().complement());
                    log.info("Updated address.");

                    client.getContact().setEmail(dto.contact().email());
                    client.getContact().setCell(dto.contact().cell());
                    log.info("Updated contact.");

                    return client;})
                .map(ClientCompanyDTOResponse::new)
                .orElseThrow(() -> {
                    log.error("Exception - resource not found.");
                    throw new ResourceNotFoundCustomException(messages.getResourceNotFound());
                });
    }

        private void validateUniqueCNPJRuleByUpdate(ClientCompanyDTORequest dto) {
            log.info("Single CNPJ rule validation.");

            var clientByCNPJ = this.repository.searchByDocument(dto.cnpj());
            if(clientByCNPJ.isPresent() && clientByCNPJ.get().getId() != dto.id()) {
                log.error("Exception - business rule violation.");
                throw new BusinessRuleViolationCustomException(messages.getSingleCnpjRuleViolation());
            }
        }

    @Override
    public ClientCompanyDTOResponse searchById(Long id) {
        log.info("Started resource fetch service by identifier.");

        return this.repository.searchById(id)
                .map(ClientCompanyDTOResponse::new)
                .orElseThrow(() -> {
                    log.error("Exception - resource not found.");
                    throw new ResourceNotFoundCustomException(messages.getResourceNotFound());
                });
    }

    @Override
    public Page<ClientCompanyDTOResponse> searchAll(ClientCompanyFilter filter, Pageable pagination) {
        log.info("Started search service all resources.");

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
        log.info("Started resource deletion service by identifier.");

        return this.repository.searchById(id)
                .map(client -> {
                    this.repository.deleteById(client.getId());
                    log.info("Resource deleted successfully.");

                    return messages.getResourceDeletedSuccessfully();})
                .orElseThrow(() -> {
                    log.error("Exception - resource not found.");
                    throw new ResourceNotFoundCustomException(messages.getResourceNotFound());
                });
    }
}
