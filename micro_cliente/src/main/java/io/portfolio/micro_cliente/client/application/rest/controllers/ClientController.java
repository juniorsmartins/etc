package io.portfolio.micro_cliente.client.application.rest.controllers;

import io.portfolio.micro_cliente.client.domain.dtos.ClientDTORequest;
import io.portfolio.micro_cliente.client.domain.dtos.ClientDTOResponse;
import io.portfolio.micro_cliente.client.domain.dtos.PolicyDTO;
import io.portfolio.micro_cliente.client.domain.entities.ClientEntity;
import io.portfolio.micro_cliente.client.domain.entities.PolicyEntity;
import io.portfolio.micro_cliente.client.domain.filter.ClientFilter;
import io.portfolio.micro_cliente.client.domain.filter.PolicyFilter;
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
@RequestMapping(value = "${app.api.base}/assuntos", produces = {"application/json"})
public final class ClientController extends PolicyControllers<ClientDTORequest, ClientFilter, ClientDTOResponse, Long> {

    @Autowired
    private PolicyService<ClientDTORequest, ClientFilter, ClientDTOResponse, ClientEntity, Long> service;

    @Override
    public ResponseEntity<ClientDTOResponse> cadastrar(@RequestBody @Valid ClientDTORequest dto) {
        return null;
    }

    @Override
    public ResponseEntity<ClientDTOResponse> atualizar(@RequestBody @Valid ClientDTORequest dto) {
        return null;
    }

    @Override
    public ResponseEntity<ClientDTOResponse> consultarPorId(@PathVariable(value = "id") Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Page<ClientDTOResponse>> buscarTodos(ClientFilter filter,
               @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
        return null;
    }

    @Override
    public ResponseEntity<?> deletarPorId(@PathVariable(value = "id") Long id) {
        return null;
    }
}
