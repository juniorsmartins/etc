package io.portfolio.micro_cliente.client.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.portfolio.micro_cliente.client.domain.client.ClientPersonEntity;
import io.portfolio.micro_cliente.client.domain.enums.EducationEnum;
import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.MaritalStatusEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClientPersonDTOResponse
    (
        @JsonProperty("Identifier")
        Long id,

        @JsonProperty("First Name")
        String firstName,

        @JsonProperty("Last Name")
        String lastName,

        @JsonProperty("Individual Registration") // Renomeia o nome do campo para a apresentação
        String cpf,

        @JsonProperty("Birth Date")
        LocalDate birthDate,

        @JsonProperty("Sex")
        SexEnum sex,

        @JsonProperty("Genre")
        GenreEnum genre,

        @JsonProperty("Marital Status")
        MaritalStatusEnum maritalStatus,

        @JsonProperty("Education")
        EducationEnum education,

        @JsonProperty("Address")
        AddressDTOResponse address,

        @JsonProperty("Contact")
        ContactDTOResponse contact
    ) implements PolicyDTO<Long>
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
