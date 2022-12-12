package io.portfolio.micro_cliente.client.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.portfolio.micro_cliente.client.domain.client.ClientCompanyEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClientCompanyDTOResponse
    (
        @JsonProperty("Identifier")
        Long id,

        @JsonProperty("Business Name")
        String businessName,

        @JsonProperty("Fantasy Name")
        String fantasyName,

        @JsonProperty("National Register of Legal Entities")
        String cnpj,

        @JsonIgnore
        LocalDate birthDate,

        @JsonProperty("Address")
        AddressDTOResponse address,

        @JsonProperty("Contact")
        ContactDTOResponse contact
    ) implements PolicyDTO<Long>
{
    private static Logger log = LoggerFactory.getLogger(ClientCompanyDTOResponse.class);

    public ClientCompanyDTOResponse(ClientCompanyEntity companyEntity) {
        this(companyEntity.getId(),
            companyEntity.getBusinessName(),
            companyEntity.getFantasyName(),
            companyEntity.getCnpj(),
            companyEntity.getBirthDate(),
            new AddressDTOResponse(companyEntity.getAddress()),
            new ContactDTOResponse(companyEntity.getContact()));
        log.info("DTOResponse - company entity conversion to return DTO.");
    }
}
