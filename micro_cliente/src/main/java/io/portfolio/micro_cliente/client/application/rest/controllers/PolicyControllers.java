package io.portfolio.micro_cliente.client.application.rest.controllers;

import io.portfolio.micro_cliente.client.domain.dtos.PolicyDTO;
import io.portfolio.micro_cliente.client.domain.filter.PolicyFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public sealed abstract class PolicyControllers<R extends PolicyDTO<ID>, F extends PolicyFilter<ID>, S extends PolicyDTO<ID>, ID> permits ClientController {

    @PostMapping
    public abstract ResponseEntity<S> cadastrar(R dto);

    @PutMapping
    public abstract ResponseEntity<S> atualizar(R dto);

    @GetMapping(path = "/{id}")
    public abstract ResponseEntity<S> consultarPorId(ID id);

    @GetMapping
    public abstract ResponseEntity<Page<S>> buscarTodos(F filter, Pageable paginacao);

    @DeleteMapping(path = "/{id}")
    public abstract ResponseEntity<?> deletarPorId(ID id);
}
