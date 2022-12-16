package io.portfolio.micro_cliente.client.domain.ports;

import io.portfolio.micro_cliente.client.domain.entities.user.PolicyUserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PolicyUserRepository<P extends PolicyUserEntity<ID>, ID> {

    P saveEntity(P entity);
    Optional<P> searchById(ID id);
    void deleteById(ID id);
}
