package io.portfolio.micro_cliente.client.domain.dtos_response.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.portfolio.micro_cliente.client.domain.entities.user.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Schema(name = "User DTOResponse", description = "structure for transporting data.")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDTOResponse
    (
        @Schema(name = "Id", description = "identification key", type = "Long", example = "22")
        @JsonProperty("Identifier")
        Long id,

        @Schema(name = "Email Login", description = "login email address", type = "String", example = "Francisco")
        @JsonProperty("Email Login")
        String emailLogin,

        @JsonIgnore
        String password
    )
{
    private static Logger log = LoggerFactory.getLogger(UserDTOResponse.class);

    public UserDTOResponse(UserEntity user) {
        this(user.getId(), user.getEmailLogin(), user.getPassword());
        log.info("user entity conversion to return DTO.");
    }
}
