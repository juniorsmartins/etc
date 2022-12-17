package io.portfolio.micro_cliente.client.domain.services.client;

import io.portfolio.micro_cliente.client.domain.entities.client.ClientPersonEntity;
import io.portfolio.micro_cliente.client.application.rest.dtos_request.client.ClientPersonDTORequest;
import io.portfolio.micro_cliente.client.domain.dtos_response.client.ClientPersonDTOResponse;
import io.portfolio.micro_cliente.client.domain.filter.ClientPersonFilter;
import io.portfolio.micro_cliente.client.domain.ports.PolicyClientRepository;
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

import java.util.Optional;

@Service
public non-sealed class ClientPersonService implements PolicyClientService<ClientPersonDTORequest, ClientPersonFilter,
        ClientPersonDTOResponse, ClientPersonEntity, Long> {

    private static Logger log = LoggerFactory.getLogger(ClientPersonService.class);

    @Autowired
    private PolicyClientRepository<ClientPersonEntity, Long> repository;

    @Autowired
    private MessagesProperties messages;

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public ClientPersonDTOResponse create(ClientPersonDTORequest dto) {
        log.info("Started resource record service.");

        return Optional.of(dto)
                .map(ClientPersonEntity::new)
                .map(clientNew -> {
                    validateUniqueCPFRule(clientNew.getCpf());

                    clientNew.getAddress().setClient(clientNew);
                    clientNew.getContact().setClient(clientNew);

                    return this.repository.saveEntity(clientNew);})
                .map(ClientPersonDTOResponse::new)
                .orElseThrow(() -> {
                    log.error("Exception - internal server error.");
                    throw new InternalErrorCustomException(messages.getUnavailableServer());
                });
    }

        private void validateUniqueCPFRule(String cpf) {
            log.info("Single CPF rule validation.");

            if(this.repository.searchByDocument(cpf).isPresent()) {
                log.error("Exception - business rule violation.");
                throw new BusinessRuleViolationCustomException(messages.getSingleCpfRuleViolation());
            }
        }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    @Override
    public ClientPersonDTOResponse update(ClientPersonDTORequest dto) {
        log.info("Started resource update service.");

        return this.repository.searchById(dto.id())
                .map(client -> {
                    validateUniqueCPFRuleByUpdate(dto);

                    client.setFirstName(dto.firstName());
                    client.setLastName(dto.lastName());
                    client.setCpf(dto.cpf());
                    client.setSex(dto.sex());
                    client.setGenre(dto.genre());
                    client.setBirthDate(dto.birthDate());
                    client.setMaritalStatus(dto.maritalStatus());
                    client.setEducation(dto.education());
                    log.info("Updated person.");

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
                .map(ClientPersonDTOResponse::new)
                .orElseThrow(() -> {
                    log.error("Exception - resource not found.");
                    throw new ResourceNotFoundCustomException(messages.getResourceNotFound());
                });
    }

        private void validateUniqueCPFRuleByUpdate(ClientPersonDTORequest dto) {
            log.info("Single CPF rule validation.");

            var clientByCPF = this.repository.searchByDocument(dto.cpf());
            if(clientByCPF.isPresent() && clientByCPF.get().getId() != dto.id()) {
                log.error("Exception - business rule violation.");
                throw new BusinessRuleViolationCustomException(messages.getSingleCpfRuleViolation());
            }
        }

    @Override
    public ClientPersonDTOResponse searchById(Long id) {
        log.info("Started resource fetch service by identifier.");

        return this.repository.searchById(id)
                .map(ClientPersonDTOResponse::new)
                .orElseThrow(() -> {
                    log.error("Exception - resource not found.");
                    throw new ResourceNotFoundCustomException(messages.getResourceNotFound());
                });
    }

    @Override
    public Page<ClientPersonDTOResponse> searchAll(ClientPersonFilter filter, Pageable pagination) {
        log.info("Started search service all resources.");

        return this.repository.searchAll(configureFilter(filter), pagination)
                .map(ClientPersonDTOResponse::new);
    }

        private Example<ClientPersonEntity> configureFilter(ClientPersonFilter filter) {
            // ExampleMatcher - permite configurar condições para serem aplicadas nos filtros
            ExampleMatcher exampleMatcher = ExampleMatcher
                    .matchingAll()
                    .withIgnoreCase() // Ignorar caixa alta ou baixa - quando String
                    .withIgnoreNullValues() // Ignorar valores nulos
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // permite encontrar palavras parecidas - tipo Like do SQL

            // Example - pega campos populados para criar filtros
            return Example.of(ClientPersonEntity.builder()
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
    public void deleteById(Long id) {
        log.info("Started resource deletion service by identifier.");

        this.repository.searchById(id)
                .map(client -> {
                    this.repository.deleteById(client.getId());
                    log.info("Resource deleted successfully.");
                    return true;})
                .orElseThrow(() -> {
                    log.error("Exception - resource not found.");
                    throw new ResourceNotFoundCustomException(messages.getResourceNotFound());
                });
    }
}
