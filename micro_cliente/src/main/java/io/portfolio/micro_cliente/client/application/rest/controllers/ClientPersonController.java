package io.portfolio.micro_cliente.client.application.rest.controllers;

import io.portfolio.micro_cliente.client.domain.client.ClientPersonEntity;
import io.portfolio.micro_cliente.client.domain.dtos.ClientPersonDTORequest;
import io.portfolio.micro_cliente.client.domain.dtos.ClientPersonDTOResponse;
import io.portfolio.micro_cliente.client.domain.filter.ClientPersonFilter;
import io.portfolio.micro_cliente.client.domain.services.PolicyService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = "${app.api.base}/clients/persons", produces = {"application/json"})
public final class ClientPersonController extends PolicyControllers<ClientPersonDTORequest, ClientPersonFilter, ClientPersonDTOResponse, Long> {

    private static Logger log = LoggerFactory.getLogger(ClientPersonController.class);

    @Autowired
    private PolicyService<ClientPersonDTORequest, ClientPersonFilter, ClientPersonDTOResponse, ClientPersonEntity, Long> service;

    @Override
    public ResponseEntity<ClientPersonDTOResponse> create(@RequestBody @Valid ClientPersonDTORequest dto) {
        log.info("Started resource record control.");

        var response = this.service.create(dto);

        log.info("Return - completed resource registration.");
        return ResponseEntity
                .created(URI.create("/"+ response.id()))
                .body(response);
    }

    @Override
    public ResponseEntity<ClientPersonDTOResponse> update(@RequestBody @Valid ClientPersonDTORequest dto) {
        log.info("Started resource update control.");

        var response = this.service.update(dto);

        log.info("Return - completed resource update");
        return ResponseEntity
                .ok()
                .body(response);
    }

    @Override
    public ResponseEntity<ClientPersonDTOResponse> searchById(@PathVariable(value = "id") Long id) {
        log.info("Started resource fetch control by identifier.");

        var response = this.service.searchById(id);

        log.info("Return - completed resource lookup by identifier.");
        return ResponseEntity
                .ok()
                .body(response);
    }

    @Override
    public ResponseEntity<Page<ClientPersonDTOResponse>> searchAll(ClientPersonFilter filter,
       @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pagination) {
        log.info("Started search control all resources.");

        var response = this.service.searchAll(filter, pagination);

        log.info("Return - completed search control all resources.");
        return ResponseEntity
                .ok()
                .body(response);
    }

    @Override
    public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id) {
        log.info("Started resource deletion control by identifier.");

        var response = this.service.deleteById(id);

        log.info("Return - completed delete resource by identifier.");
        return ResponseEntity
                .ok()
                .body(response);
    }
}
