package io.portfolio.micro_cliente.client.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.portfolio.micro_cliente.client.domain.client.ClientPersonEntityImpl;
import io.portfolio.micro_cliente.client.domain.enums.EducationEnum;
import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.MaritalStatusEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;

import java.io.Serializable;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClientPersonDTOResponseImpl
    (
        @JsonProperty("Identifier")
        Long id,

        @JsonProperty("First Name")
        String firstName,

        @JsonProperty("Last Name")
        String lastName,

        @JsonProperty("Individual Registration") // Renomeia o nome do campo para a apresentação
        String cpf,

        @JsonIgnore
        LocalDate birthDate,

        @JsonProperty("Sex")
        SexEnum sex,

        @JsonProperty("Genre")
        GenreEnum genre,

        @JsonProperty("Marital Status")
        MaritalStatusEnum maritalStatus,

        @JsonProperty("Education")
        EducationEnum education
    ) implements Serializable, PolicyDTO<Long>
{
    public ClientPersonDTOResponseImpl(ClientPersonEntityImpl clientEntity) {
        this(clientEntity.getId(),
            clientEntity.getFirstName(),
            clientEntity.getLastName(),
            clientEntity.getCpf(),
            clientEntity.getBirthDate(),
            clientEntity.getSex(),
            clientEntity.getGenre(),
            clientEntity.getMaritalStatus(),
            clientEntity.getEducation());
    }
}
