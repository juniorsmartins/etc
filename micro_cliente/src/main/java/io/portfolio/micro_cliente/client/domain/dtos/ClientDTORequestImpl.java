package io.portfolio.micro_cliente.client.domain.dtos;

import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

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
    @Length(max = 1)
    private SexEnum sex;

    @NotBlank
    @Length(max = 30)
    private GenreEnum genre;

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotBlank
    @Length(max = 30)
    private String maritalStatus;

    @NotBlank
    @Length(max = 200)
    private String education;
}
