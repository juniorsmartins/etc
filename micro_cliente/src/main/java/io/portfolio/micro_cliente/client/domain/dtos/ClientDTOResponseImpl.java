package io.portfolio.micro_cliente.client.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.portfolio.micro_cliente.client.domain.entities.ClientEntityImpl;
import io.portfolio.micro_cliente.client.domain.enums.EducationEnum;
import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.MaritalStatusEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ClientDTOResponseImpl implements Serializable, PolicyDTO<Long> {

    private Long id;
    private String firstName;
    private String lastName;
    private String cpf;
    private SexEnum sex;
    private GenreEnum genre;
    private LocalDate birthDate;
    private MaritalStatusEnum maritalStatus;
    private EducationEnum education;

    public ClientDTOResponseImpl(ClientEntityImpl clientEntity) {
        this.id = clientEntity.getId();
        this.firstName = clientEntity.getFirstName();
        this.lastName = clientEntity.getLastName();
        this.cpf = clientEntity.getCpf();
        this.sex = clientEntity.getSex();
        this.genre = clientEntity.getGenre();
        this.birthDate = clientEntity.getBirthDate();
        this.maritalStatus = clientEntity.getMaritalStatus();
        this.education = clientEntity.getEducation();
    }
}
