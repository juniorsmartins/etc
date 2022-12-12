package io.portfolio.micro_cliente.client.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.portfolio.micro_cliente.client.domain.client.ContactEntity;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ContactDTOResponse
    (
        @JsonProperty("Identifier")
        Long id,

        @JsonProperty("Email")
        String email,

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
