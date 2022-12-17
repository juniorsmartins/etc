package io.portfolio.micro_cliente.client.application.rest.dtos_request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Schema(name = "User DTORequest", description = "structure for transporting data.")
public record UserDTORequest
    (
        @Schema(name = "Login", description = "user credential", type = "String", example = "olga.weiss")
        @NotBlank
        @Length(max = 100)
        String login,

        @Schema(name = "Password", description = "secret code", type = "String", example = "abrA!.Cadabra12")
        @NotBlank
        @Length(max = 255)
        String password
    ) implements PolicyUserDTORequest<Long>
{ }
