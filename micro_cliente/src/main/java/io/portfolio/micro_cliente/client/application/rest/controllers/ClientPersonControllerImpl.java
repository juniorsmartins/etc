package io.portfolio.micro_cliente.client.application.rest.controllers;

import io.portfolio.micro_cliente.client.domain.dtos.ClientPersonDTORequestImpl;
import io.portfolio.micro_cliente.client.domain.dtos.ClientPersonDTOResponseImpl;
import io.portfolio.micro_cliente.client.domain.client.ClientPersonImpl;
import io.portfolio.micro_cliente.client.domain.filter.ClientFilterImpl;
import io.portfolio.micro_cliente.client.domain.services.PolicyService;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "${app.api.base}/clients", produces = {"application/json"})
public final class ClientControllerImpl extends PolicyControllers<ClientPersonDTORequestImpl, ClientFilterImpl, ClientPersonDTOResponseImpl, Long> {

    @Autowired
    private PolicyService<ClientPersonDTORequestImpl, ClientFilterImpl, ClientPersonDTOResponseImpl, ClientPersonImpl, Long> service;

    @Override
    public ResponseEntity<ClientPersonDTOResponseImpl> create(@RequestBody @Valid ClientPersonDTORequestImpl dto) {
        return this.service.create(dto);
    }

    @Override
    public ResponseEntity<ClientPersonDTOResponseImpl> update(@RequestBody @Valid ClientPersonDTORequestImpl dto) {
        return this.service.update(dto);
    }

    @Override
    public ResponseEntity<ClientPersonDTOResponseImpl> searchById(@PathVariable(value = "id") Long id) {
        return this.service.searchById(id);
    }

    @Override
    public ResponseEntity<Page<ClientPersonDTOResponseImpl>> searchAll(ClientFilterImpl filter,
                                                                       @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pagination) {
        return this.service.searchAll(filter, pagination);
    }

    @Override
    public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id) {
        return this.service.deleteById(id);
    }
}
