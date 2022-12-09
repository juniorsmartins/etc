package io.portfolio.micro_cliente.client.domain.dtos.address;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public record AddressDTORequest
    (
        Long idClient,

        @NotBlank
        @Length(max = 15)
        String cep,

        @NotBlank
        @Length(max = 100)
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
    ) implements Serializable
{ }
