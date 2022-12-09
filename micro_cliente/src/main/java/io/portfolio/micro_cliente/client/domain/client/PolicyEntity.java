package io.portfolio.micro_cliente.client.domain.client;

public sealed interface PolicyEntity<ID> permits ClientCompanyEntity, ClientPersonEntity {
    ID getId();
    void setId(ID id);
}

