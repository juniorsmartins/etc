package io.portfolio.micro_cliente.client.application.rest.controllers;

import io.portfolio.micro_cliente.client.domain.client.ClientPersonEntity;
import io.portfolio.micro_cliente.client.domain.dtos.ClientPersonDTORequest;
import io.portfolio.micro_cliente.client.domain.dtos.ClientPersonDTOResponse;
import io.portfolio.micro_cliente.client.domain.filter.ClientPersonFilter;
import io.portfolio.micro_cliente.client.domain.services.PolicyService;
import jakarta.validation.Valid;
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

@RestController
@RequestMapping(value = "${app.api.base}/clients/persons", produces = {"application/json"})
public final class ClientPersonController extends PolicyControllers<ClientPersonDTORequest, ClientPersonFilter, ClientPersonDTOResponse, Long> {

    @Autowired
    private PolicyService<ClientPersonDTORequest, ClientPersonFilter, ClientPersonDTOResponse,
            ClientPersonEntity, Long> service;

    @Override
    public ResponseEntity<ClientPersonDTOResponse> create(@RequestBody @Valid ClientPersonDTORequest dto) {
        return this.service.create(dto);
    }

    @Override
    public ResponseEntity<ClientPersonDTOResponse> update(@RequestBody @Valid ClientPersonDTORequest dto) {
        return this.service.update(dto);
    }

    @Override
    public ResponseEntity<ClientPersonDTOResponse> searchById(@PathVariable(value = "id") Long id) {
        return this.service.searchById(id);
    }

    @Override
    public ResponseEntity<Page<ClientPersonDTOResponse>> searchAll(ClientPersonFilter filter,
       @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pagination) {
        return this.service.searchAll(filter, pagination);
    }

    @Override
    public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id) {
        return this.service.deleteById(id);
    }
}
