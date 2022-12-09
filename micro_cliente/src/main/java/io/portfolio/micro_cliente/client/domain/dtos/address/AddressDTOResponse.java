package io.portfolio.micro_cliente.client.domain.dtos.address;

import java.io.Serializable;

public record AddressDTOResponse
    (
        Long idClient,
        String cep,
        String state,
        String city,
        String district,
        String publicPlace,
        int houseNumber,
        String complement
    ) implements Serializable
{ }
