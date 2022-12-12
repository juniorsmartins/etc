package io.portfolio.micro_cliente.client.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.portfolio.micro_cliente.client.domain.client.AddressEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AddressDTOResponse
    (
        @JsonProperty("Identifier")
        Long id,

        @JsonProperty("Cep")
        String cep,

        @JsonProperty("State")
        String state,

        @JsonProperty("City")
        String city,

        @JsonProperty("District")
        String district,

        @JsonProperty("Public Place")
        String publicPlace,

        @JsonProperty("House Number")
        int houseNumber,

        @JsonProperty("Complement")
        String complement
    )
{
    private static Logger log = LoggerFactory.getLogger(AddressDTOResponse.class);

    public AddressDTOResponse(AddressEntity address) {
        this(address.getId(),
                address.getCep(),
                address.getState(),
                address.getCity(),
                address.getDistrict(),
                address.getPublicPlace(),
                address.getHouseNumber(),
                address.getComplement());
        log.info("DTOResponse - address entity conversion to return DTO.");
    }
}
