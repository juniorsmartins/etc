package io.portfolio.micro_cliente.client.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
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
        Long id,
        String firstName,
        String lastName,
        String cpf,
        SexEnum sex,
        GenreEnum genre,
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
