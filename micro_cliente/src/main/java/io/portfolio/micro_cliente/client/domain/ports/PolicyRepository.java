package io.portfolio.micro_cliente.client.domain.ports;

import io.portfolio.micro_cliente.client.domain.client.PolicyEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PolicyRepository<P extends PolicyEntity<ID>, ID> {

    P saveEntity(P entity);
    Optional<P> searchById(ID id);
    Optional<P> searchByDocument(String document);
    Page<P> searchAll(Example filter, Pageable pagination);
    void deleteById(ID id);
}
