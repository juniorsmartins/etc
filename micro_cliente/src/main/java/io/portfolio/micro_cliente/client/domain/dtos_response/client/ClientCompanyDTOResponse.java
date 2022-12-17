package io.portfolio.micro_cliente.client.domain.dtos_response.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.portfolio.micro_cliente.client.domain.entities.client.ClientCompanyEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@Schema(name = "ClientCompany DTOResponse", description = "structure for transporting data.")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClientCompanyDTOResponse
    (
        @Schema(name = "Id", description = "identification key", type = "Long", example = "22")
        @JsonProperty("Identifier")
        Long id,

        @Schema(name = "Business Name", description = "corporate name", type = "String", example = "Oracle Foundation")
        @JsonProperty("Business Name")
        String businessName,

        @Schema(name = "Fantasy Name", description = "marketing name", type = "String", example = "Oracle")
        @JsonProperty("Fantasy Name")
        String fantasyName,

        @Schema(name = "CNPJ", description = "national registration of legal entity", type = "String", example = "75.882.706/0001-23")
        @JsonProperty("National Register of Legal Entities")
        String cnpj,

        @Schema(name = "Birth Date", description = "life registration date", type = "LocalDate", example = "23/05/1995")
        @JsonIgnore
        LocalDate birthDate,

        @Schema(name = "Address", description = "location of legal residence", type = "AddressDTOResponse")
        @JsonProperty("Address")
        AddressDTOResponse address,

        @Schema(name = "Contact", description = "personal communication channels", type = "ContactDTOResponse")
        @JsonProperty("Contact")
        ContactDTOResponse contact
    ) implements PolicyDTOResponse<Long>
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
