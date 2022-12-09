package io.portfolio.micro_cliente.client.domain.services;

import io.portfolio.micro_cliente.client.domain.dtos.PolicyDTO;
import io.portfolio.micro_cliente.client.domain.client.PolicyEntity;
import io.portfolio.micro_cliente.client.domain.filter.PolicyFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public sealed interface PolicyService<R extends PolicyDTO<ID>, F extends PolicyFilter<ID>, S extends PolicyDTO<ID>,
        E extends PolicyEntity<ID>, ID> permits ClientPersonService, ClientCompanyService
{
    ResponseEntity<S> create(R dto);

    ResponseEntity<S> update(R dto);

    ResponseEntity<S> searchById(ID id);

    ResponseEntity<Page<S>> searchAll(F filter, Pageable pagination);

    ResponseEntity<?> deleteById(ID id);
}

