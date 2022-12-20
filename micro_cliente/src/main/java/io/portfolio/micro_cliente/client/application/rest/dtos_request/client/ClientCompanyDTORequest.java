package io.portfolio.micro_cliente.client.application.rest.dtos_request.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.portfolio.micro_cliente.client.application.rest.dtos_request.user.UserDTORequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDate;

@Schema(name = "ClientCompany DTORequest", description = "structure for transporting data.")
public record ClientCompanyDTORequest
    (
        @Schema(name = "Id", description = "identification key", type = "Long", example = "22", nullable = true)
        Long id,

        @Schema(name = "Business Name", description = "corporate name", type = "String", example = "Oracle Foundation")
        @NotBlank
        @Length(max = 100)
        String businessName,

        @Schema(name = "Fantasy Name", description = "marketing name", type = "String", example = "Oracle")
        @Length(max = 100)
        String fantasyName,

        @Schema(name = "CNPJ", description = "national registration of legal entity", type = "String", example = "75.882.706/0001-23")
        @NotBlank
        @Length(max = 20)
        @CNPJ
        String cnpj,

        @Schema(name = "Birth Date", description = "life registration date", type = "LocalDate", example = "23/05/1995")
        @NotNull
        @Past
        @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        LocalDate birthDate,

        @Schema(name = "Address", description = "location of legal residence", type = "AddressDTORequest")
        @NotNull
        @Valid
        AddressDTORequest address,

        @Schema(name = "Contact", description = "personal communication channels", type = "ContactDTORequest")
        @NotNull
        @Valid
        ContactDTORequest contact,

        @Schema(name = "User", description = "access control information", type = "UserDTORequest")
        @NotNull
        @Valid
        UserDTORequest user
    ) implements PolicyClientDTORequest<Long>
{ }
