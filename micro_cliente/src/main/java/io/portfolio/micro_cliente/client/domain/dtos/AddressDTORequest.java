package io.portfolio.micro_cliente.client.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Schema(name = "AddressDTORequest", description = "structure for transporting data.")
public record AddressDTORequest
    (
        @Schema(name = "Id", description = "identification key", type = "Long", example = "22", nullable = true)
        Long id,

        @Schema(name = "Cep", description = "zip code", type = "String", example = "78.000-000")
        @NotBlank
        @Length(max = 15)
        String cep,

        @Schema(name = "State", description = "federative unit", type = "String", example = "RS")
        @NotBlank
        @Length(max = 2)
        String state,

        @Schema(name = "City", description = "urban area", type = "String", example = "Porto Alegre")
        @NotBlank
        @Length(max = 100)
        String city,

        @Schema(name = "District", description = "region within the city", type = "String", example = "Centro-Sul")
        @NotBlank
        @Length(max = 100)
        String district,

        @Schema(name = "Public Place", description = "definition of avenues or streets", type = "String", example = "Avenue Independência")
        @NotBlank
        @Length(max = 100)
        String publicPlace,

        @Schema(name = "House Number", description = "property number", type = "int", example = "1550")
        int houseNumber,

        @Schema(name = "Complement", description = "additional information about the property", type = "String", example = "side entrance")
        @Length(max = 250)
        String complement
    )
{ }
