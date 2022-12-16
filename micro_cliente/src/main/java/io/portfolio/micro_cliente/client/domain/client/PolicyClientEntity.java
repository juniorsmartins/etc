package io.portfolio.micro_cliente.client.domain.client;

public sealed interface PolicyClientEntity<ID> permits ClientCompanyEntity, ClientPersonEntity {
    ID getId();
}

