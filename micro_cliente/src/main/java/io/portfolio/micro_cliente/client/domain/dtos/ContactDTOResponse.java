package io.portfolio.micro_cliente.client.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.portfolio.micro_cliente.client.domain.client.ContactEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Schema(name = "Contact DTOResponse", description = "structure for transporting data.")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ContactDTOResponse
    (
        @Schema(name = "Id", description = "identification key", type = "Long", example = "22")
        @JsonProperty("Identifier")
        Long id,

        @Schema(name = "Email", description = "electronic mail recipient", type = "String", example = "fulano@email.com")
        @JsonProperty("Email")
        String email,

        @Schema(name = "Cell", description = "mobile phone number", type = "String", example = "(55)9999-9999")
        @JsonProperty("Cell")
        String cell
    )
{
    private static Logger log = LoggerFactory.getLogger(ContactDTOResponse.class);

    public ContactDTOResponse(ContactEntity contact) {
        this(contact.getId(),
                contact.getEmail(),
                contact.getCell());
        log.info("DTOResponse - contact entity conversion to return DTO.");
    }
}
