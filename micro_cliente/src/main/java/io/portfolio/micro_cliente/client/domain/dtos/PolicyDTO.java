package io.portfolio.micro_cliente.client.domain.dtos;

public sealed interface PolicyDTO<ID> permits ClientPersonDTORequestImpl, ClientPersonDTOResponseImpl,
        ClientCompanyDTORequestImpl, ClientCompanyDTOResponseImpl { }

