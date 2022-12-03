package io.portfolio.micro_cliente.client.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.portfolio.micro_cliente.client.domain.entities.ClientEntityImpl;
import io.portfolio.micro_cliente.client.domain.enums.EducationEnum;
import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.MaritalStatusEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;

import java.io.Serializable;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClientDTOResponseImpl
    (
        @JsonProperty("Identifier")
        Long id,

        String firstName,
        String lastName,

        @JsonProperty("Individual Registration") // Renomeia o nome do campo para a apresentação
        String cpf,

        SexEnum sex,
        GenreEnum genre,

        @JsonIgnore // Oculta a apresentação desse atributo
        LocalDate birthDate,

        MaritalStatusEnum maritalStatus,
        EducationEnum education

    ) implements Serializable, PolicyDTO<Long>
{
    public ClientDTOResponseImpl(ClientEntityImpl clientEntity) {
        this(clientEntity.getId(),
            clientEntity.getFirstName(),
            clientEntity.getLastName(),
            clientEntity.getCpf(),
            clientEntity.getSex(),
            clientEntity.getGenre(),
            clientEntity.getBirthDate(),
            clientEntity.getMaritalStatus(),
            clientEntity.getEducation());
    }
}
