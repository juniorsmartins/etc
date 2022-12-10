package io.portfolio.micro_cliente.client.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ContactDTORequest
    (
        Long id,

        @NotBlank
        @Length(max = 100)
        @Email
        String email,

        @NotBlank
        @Length(max = 20)
        String cell
    )
{ }
