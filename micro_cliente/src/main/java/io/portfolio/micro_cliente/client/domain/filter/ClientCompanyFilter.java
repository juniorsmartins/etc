package io.portfolio.micro_cliente.client.domain.filter;

import java.io.Serializable;

public record ClientCompanyFilter
    (
        String businessName,
        String fantasyName,
        String cnpj
    ) implements Serializable, PolicyFilter<Long>
{ }
