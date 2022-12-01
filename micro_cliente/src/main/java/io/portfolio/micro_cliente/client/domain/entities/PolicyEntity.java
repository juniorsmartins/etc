package io.portfolio.micro_cliente.client.domain.entities;

public sealed interface PolicyEntity<ID> permits ClientEntityImpl {
    ID getId();
    void setId(ID id);
}
