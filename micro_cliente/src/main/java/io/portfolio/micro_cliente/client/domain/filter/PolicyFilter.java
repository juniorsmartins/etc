package io.portfolio.micro_cliente.client.domain.filter;

public sealed interface PolicyFilter<ID> permits ClientPersonFilter, ClientCompanyFilter
{ }
