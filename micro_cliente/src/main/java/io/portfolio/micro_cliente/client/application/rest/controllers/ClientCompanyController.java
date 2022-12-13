package io.portfolio.micro_cliente.client.application.rest.controllers;

import io.portfolio.micro_cliente.client.domain.client.ClientCompanyEntity;
import io.portfolio.micro_cliente.client.domain.dtos.ClientCompanyDTORequest;
import io.portfolio.micro_cliente.client.domain.dtos.ClientCompanyDTOResponse;
import io.portfolio.micro_cliente.client.domain.filter.ClientCompanyFilter;
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
// @RequestMapping(value = "${app.api.base}/clients/companys", produces = {"application/json"})
@RequestMapping(value = "/companys", produces = {"application/json"})
public final class ClientCompanyController extends PolicyControllers<ClientCompanyDTORequest, ClientCompanyFilter, ClientCompanyDTOResponse, Long> {

    private static Logger log = LoggerFactory.getLogger(ClientCompanyController.class);

    @Autowired
    private PolicyService<ClientCompanyDTORequest, ClientCompanyFilter, ClientCompanyDTOResponse, ClientCompanyEntity, Long> service;

    @Override
    public ResponseEntity<ClientCompanyDTOResponse> create(@RequestBody @Valid ClientCompanyDTORequest dto) {
        log.info("Started resource record control.");

        var response = this.service.create(dto);

        log.info("Return - completed resource registration.");
        return ResponseEntity
                .created(URI.create("/" + response.id()))
                .body(response);
    }

    @Override
    public ResponseEntity<ClientCompanyDTOResponse> update(@RequestBody @Valid ClientCompanyDTORequest dto) {
        log.info("Started resource update control.");

        var response = this.service.update(dto);

        log.info("Return - completed resource update");
        return ResponseEntity
                .ok()
                .body(response);
    }

    @Override
    public ResponseEntity<ClientCompanyDTOResponse> searchById(@PathVariable(value = "id") final Long id) {
        log.info("Started resource fetch control by identifier.");

        var response = this.service.searchById(id);

        log.info("Return - completed resource lookup by identifier.");
        return ResponseEntity
                .ok()
                .body(response);
    }

    @Override
    public ResponseEntity<Page<ClientCompanyDTOResponse>> searchAll(ClientCompanyFilter filter,
        @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pagination) {
        log.info("Started search control all resources.");

        var response = this.service.searchAll(filter, pagination);

        log.info("Return - completed search control all resources.");
        return ResponseEntity
                .ok()
                .body(response);
    }

    @Override
    public ResponseEntity<String> deleteById(@PathVariable(value = "id") final Long id) {
        log.info("Started resource deletion control by identifier.");

        var response = this.service.deleteById(id);

        log.info("Return - completed delete resource by identifier.");
        return ResponseEntity
                .ok()
                .body(response);
    }
}
