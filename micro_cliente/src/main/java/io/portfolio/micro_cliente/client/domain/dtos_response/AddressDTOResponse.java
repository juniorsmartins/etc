package io.portfolio.micro_cliente.client.domain.dtos_response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.portfolio.micro_cliente.client.domain.client.AddressEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Schema(name = "Address DTOResponse", description = "structure for transporting data.")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record AddressDTOResponse
    (
        @Schema(name = "Id", description = "identification key", type = "Long", example = "22")
        @JsonProperty("Identifier")
        Long id,

        @Schema(name = "Cep", description = "zip code", type = "String", example = "78.000-000")
        @JsonProperty("Cep")
        String cep,

        @Schema(name = "State", description = "federative unit", type = "String", example = "RS")
        @JsonProperty("State")
        String state,

        @Schema(name = "City", description = "urban area", type = "String", example = "Porto Alegre")
        @JsonProperty("City")
        String city,

        @Schema(name = "District", description = "region within the city", type = "String", example = "Centro-Sul")
        @JsonProperty("District")
        String district,

        @Schema(name = "Public Place", description = "definition of avenues or streets", type = "String", example = "Avenue Independência")
        @JsonProperty("Public Place")
        String publicPlace,

        @Schema(name = "House Number", description = "property number", type = "int", example = "1550")
        @JsonProperty("House Number")
        int houseNumber,

        @Schema(name = "Complement", description = "additional information about the property", type = "String", example = "side entrance")
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
