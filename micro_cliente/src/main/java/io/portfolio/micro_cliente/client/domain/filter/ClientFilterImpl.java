package io.portfolio.micro_cliente.client.domain.filter;

import io.portfolio.micro_cliente.client.domain.enums.EducationEnum;
import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.MaritalStatusEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;

import java.time.LocalDate;

public record ClientFilterImpl
    (
        Long id,
        String firstName,
        String lastName,
        String cpf,
        SexEnum sex,
        GenreEnum genre,
        LocalDate birthDate,
        MaritalStatusEnum maritalStatus,
        EducationEnum education
    ) implements PolicyFilter<Long>
{ }

