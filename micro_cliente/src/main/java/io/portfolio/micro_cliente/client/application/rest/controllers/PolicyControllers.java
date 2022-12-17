package io.portfolio.micro_cliente.client.application.rest.controllers;

import io.portfolio.micro_cliente.client.application.rest.dtos_request.client.PolicyClientDTORequest;
import io.portfolio.micro_cliente.client.domain.dtos_response.client.PolicyDTOResponse;
import io.portfolio.micro_cliente.client.domain.filter.PolicyFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.util.UriComponentsBuilder;

public sealed abstract class PolicyControllers<R extends PolicyClientDTORequest<ID>, F extends PolicyFilter<ID>,
        S extends PolicyDTOResponse<ID>, ID> permits ClientPersonController, ClientCompanyController {

    @PostMapping
    public abstract ResponseEntity<S> create(R dto, UriComponentsBuilder uri);

    @PutMapping
    public abstract ResponseEntity<S> update(R dto);

    @GetMapping(path = "/{id}")
    public abstract ResponseEntity<S> searchById(ID id);

    @GetMapping
    public abstract ResponseEntity<Page<S>> searchAll(F filter, Pageable pagination);

    @DeleteMapping(path = "/{id}")
    public abstract ResponseEntity deleteById(ID id);
}

