package io.portfolio.micro_cliente.client.domain.services;

import io.portfolio.micro_cliente.client.domain.dtos.PolicyDTO;
import io.portfolio.micro_cliente.client.domain.entities.PolicyEntity;
import io.portfolio.micro_cliente.client.domain.filter.PolicyFilter;
import org.springframework.stereotype.Service;

@Service
public sealed interface PolicyService<R extends PolicyDTO<ID>, F extends PolicyFilter<ID>, S extends PolicyDTO<ID>,
        E extends PolicyEntity<ID>, ID> permits ClientServiceImpl {
}

