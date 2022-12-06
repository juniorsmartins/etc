package io.portfolio.micro_cliente.client.domain.filter;

import java.io.Serializable;

public record ClientCompanyFilterImpl
        () implements Serializable, PolicyFilter<Long>
{ }
