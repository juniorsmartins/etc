package io.portfolio.micro_cliente.client.application.rest.controllers;

import io.portfolio.micro_cliente.client.domain.dtos.ClientDTORequestImpl;
import io.portfolio.micro_cliente.client.domain.dtos.ClientDTOResponseImpl;
import io.portfolio.micro_cliente.client.domain.entities.ClientEntityImpl;
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
public final class ClientControllerImpl extends PolicyControllers<ClientDTORequestImpl, ClientFilterImpl, ClientDTOResponseImpl, Long> {

    @Autowired
    private PolicyService<ClientDTORequestImpl, ClientFilterImpl, ClientDTOResponseImpl, ClientEntityImpl, Long> service;

    @Override
    public ResponseEntity<ClientDTOResponseImpl> create(@RequestBody @Valid ClientDTORequestImpl dto) {
        return service.create(dto);
    }

    @Override
    public ResponseEntity<ClientDTOResponseImpl> update(@RequestBody @Valid ClientDTORequestImpl dto) {
        return null;
    }

    @Override
    public ResponseEntity<ClientDTOResponseImpl> searchById(@PathVariable(value = "id") Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Page<ClientDTOResponseImpl>> searchAll(ClientFilterImpl filter,
             @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id) {
        return this.service.deleteById(id);
    }
}
