package io.portfolio.micro_cliente.client.domain.services.client;

import io.portfolio.micro_cliente.client.application.rest.dtos_request.PolicyDTORequest;
import io.portfolio.micro_cliente.client.domain.entities.client.PolicyClientEntity;
import io.portfolio.micro_cliente.client.domain.dtos_response.PolicyDTOResponse;
import io.portfolio.micro_cliente.client.domain.filter.PolicyFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public sealed interface PolicyService<R extends PolicyDTORequest<ID>, F extends PolicyFilter<ID>, S extends PolicyDTOResponse<ID>,
        E extends PolicyClientEntity<ID>, ID> permits ClientPersonService, ClientCompanyService
{
    S create(R dto);

    S update(R dto);

    S searchById(ID id);

    Page<S> searchAll(F filter, Pageable pagination);

    void deleteById(ID id);
}

