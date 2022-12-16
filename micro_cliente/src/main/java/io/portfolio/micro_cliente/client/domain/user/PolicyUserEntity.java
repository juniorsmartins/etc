package io.portfolio.micro_cliente.client.domain.user;

public sealed interface PolicyUserEntity<ID> permits UserEntity {
    ID getId();
}

