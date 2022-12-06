package io.portfolio.micro_cliente.client.domain.filter;

import io.portfolio.micro_cliente.client.domain.enums.EducationEnum;
import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.MaritalStatusEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;

import java.io.Serializable;

public record ClientPersonFilterImpl
    (
        String firstName,
        String lastName,
        String cpf,
        SexEnum sex,
        GenreEnum genre,
        MaritalStatusEnum maritalStatus,
        EducationEnum education
    ) implements Serializable, PolicyFilter<Long>
{ }

