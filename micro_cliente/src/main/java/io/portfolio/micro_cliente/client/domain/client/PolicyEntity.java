package io.portfolio.micro_cliente.client.domain.client;

public sealed interface PolicyEntity<ID> permits ClientPersonImpl, ClientCompanyImpl {
    ID getId();
    void setId(ID id);
}
