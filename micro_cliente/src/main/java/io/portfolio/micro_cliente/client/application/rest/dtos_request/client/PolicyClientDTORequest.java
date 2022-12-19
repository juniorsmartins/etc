package io.portfolio.micro_cliente.client.application.rest.dtos_request.client;

public sealed interface PolicyClientDTORequest<ID> permits ClientPersonDTORequest, ClientCompanyDTORequest {
    ID id();
}

