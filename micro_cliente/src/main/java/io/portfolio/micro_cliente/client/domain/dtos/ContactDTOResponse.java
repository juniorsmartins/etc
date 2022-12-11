package io.portfolio.micro_cliente.client.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.portfolio.micro_cliente.client.domain.client.ContactEntity;

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
    public ContactDTOResponse(ContactEntity contact) {
        this(contact.getId(),
                contact.getEmail(),
                contact.getCell());
    }
}
