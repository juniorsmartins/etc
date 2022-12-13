package io.portfolio.micro_cliente.client.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.portfolio.micro_cliente.client.domain.enums.EducationEnum;
import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.MaritalStatusEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Schema(name = "ClientPerson DTORequest", description = "structure for transporting data.")
public record ClientPersonDTORequest
    (
        @Schema(name = "Id", description = "identification key", type = "Long", example = "22", nullable = true)
        Long id,

        @Schema(name = "First Name", description = "personal name", type = "String", example = "Francisco")
        @NotBlank
        @Length(max = 30)
        String firstName,

        @Schema(name = "Last Name", description = "family name", type = "String", example = "Carvalho")
        @NotBlank
        @Length(max = 30)
        String lastName,

        @Schema(name = "CPF", description = "individual registration", type = "String", example = "806.419.420-28")
        @NotBlank
        @Length(max = 15)
        @CPF
        String cpf,

        @Schema(name = "Birth Date", description = "life registration date", type = "LocalDate", example = "23/05/1995")
        @NotNull
        @Past
        @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        LocalDate birthDate,

        @Schema(name = "Sex", description = "biological sex", type = "String", example = "MASCULINE")
        @NotNull
        SexEnum sex,

        @Schema(name = "Genre", description = "gender identity", type = "String", example = "ASEXUAL")
        @NotNull
        GenreEnum genre,

        @Schema(name = "Marital Status", description = "relationship with partner", type = "String", example = "SINGLE")
        @NotNull
        MaritalStatusEnum maritalStatus,

        @Schema(name = "Education", description = "level of schooling", type = "String", example = "COMPLETE_HIGHER_EDUCATION")
        @NotNull
        EducationEnum education,

        @Schema(name = "Address", description = "location of legal residence", type = "AddressDTORequest")
        @NotNull
        @Valid
        AddressDTORequest address,

        @Schema(name = "Contact", description = "personal communication channels", type = "ContactDTORequest")
        @NotNull
        @Valid
        ContactDTORequest contact
    ) implements PolicyDTO<Long>
{ }
