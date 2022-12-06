package io.portfolio.micro_cliente.client.application.rest.controllers;

import io.portfolio.micro_cliente.client.domain.client.ClientPersonEntityImpl;
import io.portfolio.micro_cliente.client.domain.dtos.ClientPersonDTORequestImpl;
import io.portfolio.micro_cliente.client.domain.dtos.ClientPersonDTOResponseImpl;
import io.portfolio.micro_cliente.client.domain.filter.ClientPersonFilterImpl;
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
public final class ClientPersonControllerImpl extends PolicyControllers<ClientPersonDTORequestImpl, ClientPersonFilterImpl, ClientPersonDTOResponseImpl, Long> {

    @Autowired
    private PolicyService<ClientPersonDTORequestImpl, ClientPersonFilterImpl, ClientPersonDTOResponseImpl,
            ClientPersonEntityImpl, Long> service;

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
    public ResponseEntity<Page<ClientPersonDTOResponseImpl>> searchAll(ClientPersonFilterImpl filter,
        @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pagination) {
        return this.service.searchAll(filter, pagination);
    }

    @Override
    public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id) {
        return this.service.deleteById(id);
    }
}
