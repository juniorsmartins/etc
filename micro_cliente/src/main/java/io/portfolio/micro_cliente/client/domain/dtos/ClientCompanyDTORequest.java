package io.portfolio.micro_cliente.client.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.portfolio.micro_cliente.client.domain.dtos.address.AddressDTORequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import java.io.Serializable;
import java.time.LocalDate;

public record ClientCompanyDTORequest
    (
        Long id,

        @NotBlank
        @Length(max = 100)
        String businessName,

        @Length(max = 100)
        String fantasyName,

        @NotBlank
        @Length(max = 20)
        @CNPJ
        String cnpj,

        @NotNull
        @Past
        @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        LocalDate birthDate,

        @Valid
        AddressDTORequest address
    ) implements Serializable, PolicyDTO<Long>
{ }
