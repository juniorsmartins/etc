package io.portfolio.micro_cliente.client.domain.dtos;

public sealed interface PolicyDTO<ID> permits ClientDTORequestImpl, ClientDTOResponseImpl {
    ID getId();
}
