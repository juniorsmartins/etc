package io.portfolio.micro_cliente.client.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.portfolio.micro_cliente.client.domain.enums.EducationEnum;
import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.MaritalStatusEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private SexEnum sex;
    @Enumerated(EnumType.STRING)
    private GenreEnum genre;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private MaritalStatusEnum maritalStatus;
    @Enumerated(EnumType.STRING)
    private EducationEnum education;
}
