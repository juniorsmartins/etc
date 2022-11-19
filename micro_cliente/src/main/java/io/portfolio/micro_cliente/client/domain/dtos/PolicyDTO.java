package io.portfolio.micro_cliente.client.domain.dtos;

public sealed interface PolicyDTO<ID> permits ClientDTORequest, ClientDTOResponse {
    ID getId();
}
