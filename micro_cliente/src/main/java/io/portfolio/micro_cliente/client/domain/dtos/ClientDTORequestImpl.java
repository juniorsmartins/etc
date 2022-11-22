package io.portfolio.micro_cliente.client.domain.dtos;

import io.portfolio.micro_cliente.client.domain.enums.EducationEnum;
import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.MaritalStatusEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class ClientDTORequestImpl implements Serializable, PolicyDTO<Long> {

    private Long id;

    @NotBlank
    @Length(max = 40)
    private String firstName;

    @NotBlank
    @Length(max = 40)
    private String lastName;

    @NotBlank
    @Length(max = 15)
    @CPF
    private String cpf;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private SexEnum sex;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private MaritalStatusEnum maritalStatus;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private EducationEnum education;
}
