package io.portfolio.micro_cliente.client.domain.ports;

import io.portfolio.micro_cliente.client.domain.entities.PolicyEntity;
import io.portfolio.micro_cliente.client.domain.filter.PolicyFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PolicyRepository<P extends PolicyEntity<ID>, F extends PolicyFilter<ID>, ID> {

    P create(P entity);
    Optional<P> search(P entity);
    Optional<P> searchById(ID id);
    Optional<P> searchByCpf(String cpf);
    Page<P> searchAll(F filter, Pageable pagination);
    void deleteById(ID id);
    void delete(P entity);
}
