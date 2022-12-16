package io.portfolio.micro_cliente.client.domain.entities.user;

public sealed interface PolicyUserEntity<ID> permits UserEntity {
    ID getId();
}

