package io.portfolio.micro_cliente.client.application.rest.dtos_request.client;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Schema(name = "Contact DTORequest", description = "structure for transporting data.")
public record ContactDTORequest
    (
        @Schema(name = "Id", description = "identification key", type = "Long", example = "22", nullable = true)
        Long id,

        @Schema(name = "Email", description = "electronic mail recipient", type = "String", example = "fulano@email.com")
        @NotBlank
        @Length(max = 100)
        @Email
        String email,

        @Schema(name = "Cell", description = "mobile phone number", type = "String", example = "(55)9999-9999")
        @NotBlank
        @Length(max = 20)
        String cell
    )
{ }
