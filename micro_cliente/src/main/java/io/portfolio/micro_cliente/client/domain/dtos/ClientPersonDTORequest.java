package io.portfolio.micro_cliente.client.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.portfolio.micro_cliente.client.domain.dtos.address.AddressDTORequest;
import io.portfolio.micro_cliente.client.domain.enums.EducationEnum;
import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.MaritalStatusEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;

public record ClientPersonDTORequest
    (
        Long id,

        @NotBlank
        @Length(max = 30)
        String firstName,

        @NotBlank
        @Length(max = 30)
        String lastName,

        @NotBlank
        @Length(max = 15)
        @CPF
        String cpf,

        @NotNull
        @Past
        @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        LocalDate birthDate,

        @NotNull
        SexEnum sex,

        @NotNull
        GenreEnum genre,

        @NotNull
        MaritalStatusEnum maritalStatus,

        @NotNull
        EducationEnum education,

        @Valid
        AddressDTORequest address
    ) implements Serializable, PolicyDTO<Long>
{ }
