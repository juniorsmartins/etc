package io.portfolio.micro_cliente.client.domain.filter;

import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
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
        String maritalStatus,
        String education
    ) implements PolicyFilter<Long>
{ }

