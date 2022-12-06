package io.portfolio.micro_cliente.client.application.rest.controllers;

import io.portfolio.micro_cliente.client.domain.client.ClientCompanyEntityImpl;
import io.portfolio.micro_cliente.client.domain.dtos.ClientCompanyDTORequestImpl;
import io.portfolio.micro_cliente.client.domain.dtos.ClientCompanyDTOResponseImpl;
import io.portfolio.micro_cliente.client.domain.filter.ClientCompanyFilterImpl;
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
@RequestMapping(value = "${app.api.base}/clients/companys", produces = {"application/json"})
public final class ClientCompanyControllerImpl extends PolicyControllers<ClientCompanyDTORequestImpl, ClientCompanyFilterImpl, ClientCompanyDTOResponseImpl, Long> {

    @Autowired
    private PolicyService<ClientCompanyDTORequestImpl, ClientCompanyFilterImpl, ClientCompanyDTOResponseImpl,
            ClientCompanyEntityImpl, Long> service;

    @Override
    public ResponseEntity<ClientCompanyDTOResponseImpl> create(@RequestBody @Valid ClientCompanyDTORequestImpl dto) {
        return this.service.create(dto);
    }

    @Override
    public ResponseEntity<ClientCompanyDTOResponseImpl> update(@RequestBody @Valid ClientCompanyDTORequestImpl dto) {
        return this.service.update(dto);
    }

    @Override
    public ResponseEntity<ClientCompanyDTOResponseImpl> searchById(@PathVariable(value = "id") Long id) {
        return this.service.searchById(id);
    }

    @Override
    public ResponseEntity<Page<ClientCompanyDTOResponseImpl>> searchAll(ClientCompanyFilterImpl filter,
        @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pagination) {
        return this.service.searchAll(filter, pagination);
    }

    @Override
    public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id) {
        return this.service.deleteById(id);
    }
}
