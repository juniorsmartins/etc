package io.portfolio.micro_cliente.client.domain.entities.client;

public sealed interface PolicyClientEntity<ID> permits ClientCompanyEntity, ClientPersonEntity {
    ID getId();
}

