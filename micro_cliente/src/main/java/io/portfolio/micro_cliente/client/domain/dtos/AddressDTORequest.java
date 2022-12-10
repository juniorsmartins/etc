package io.portfolio.micro_cliente.client.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public record AddressDTORequest
    (
        Long id,

        @NotBlank
        @Length(max = 15)
        String cep,

        @NotBlank
        @Length(max = 2)
        String state,

        @NotBlank
        @Length(max = 100)
        String city,

        @NotBlank
        @Length(max = 100)
        String district,

        @NotBlank
        @Length(max = 100)
        String publicPlace,

        int houseNumber,

        @Length(max = 250)
        String complement
    ) implements Serializable, PolicyDTO<Long>
{ }
