package io.portfolio.micro_cliente.client.domain.dtos_response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.portfolio.micro_cliente.client.domain.client.ClientPersonEntity;
import io.portfolio.micro_cliente.client.domain.enums.EducationEnum;
import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.MaritalStatusEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@Schema(name = "ClientPerson DTOResponse", description = "structure for transporting data.")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClientPersonDTOResponse
    (
        @Schema(name = "Id", description = "identification key", type = "Long", example = "22")
        @JsonProperty("Identifier")
        Long id,

        @Schema(name = "First Name", description = "personal name", type = "String", example = "Francisco")
        @JsonProperty("First Name")
        String firstName,

        @Schema(name = "Last Name", description = "family name", type = "String", example = "Carvalho")
        @JsonProperty("Last Name")
        String lastName,

        @Schema(name = "CPF", description = "individual registration", type = "String", example = "806.419.420-28")
        @JsonProperty("Individual Registration") // Renomeia o nome do campo para a apresentação
        String cpf,

        @Schema(name = "Birth Date", description = "life registration date", type = "LocalDate", example = "23/05/1995")
        @JsonProperty("Birth Date")
        LocalDate birthDate,

        @Schema(name = "Sex", description = "biological sex", type = "String", example = "MASCULINE")
        @JsonProperty("Sex")
        SexEnum sex,

        @Schema(name = "Genre", description = "gender identity", type = "String", example = "ASEXUAL")
        @JsonProperty("Genre")
        GenreEnum genre,

        @Schema(name = "Marital Status", description = "relationship with partner", type = "String", example = "SINGLE")
        @JsonProperty("Marital Status")
        MaritalStatusEnum maritalStatus,

        @Schema(name = "Education", description = "level of schooling", type = "String", example = "COMPLETE_HIGHER_EDUCATION")
        @JsonProperty("Education")
        EducationEnum education,

        @Schema(name = "Address", description = "location of legal residence", type = "AddressDTOResponse")
        @JsonProperty("Address")
        AddressDTOResponse address,

        @Schema(name = "Contact", description = "personal communication channels", type = "ContactDTOResponse")
        @JsonProperty("Contact")
        ContactDTOResponse contact
    ) implements PolicyDTOResponse<Long>
{
    private static Logger log = LoggerFactory.getLogger(ClientPersonDTOResponse.class);

    public ClientPersonDTOResponse(ClientPersonEntity clientEntity) {
        this(clientEntity.getId(),
            clientEntity.getFirstName(),
            clientEntity.getLastName(),
            clientEntity.getCpf(),
            clientEntity.getBirthDate(),
            clientEntity.getSex(),
            clientEntity.getGenre(),
            clientEntity.getMaritalStatus(),
            clientEntity.getEducation(),
            new AddressDTOResponse(clientEntity.getAddress()),
            new ContactDTOResponse(clientEntity.getContact()));
        log.info("DTOResponse - person entity conversion to return DTO.");
    }
}
