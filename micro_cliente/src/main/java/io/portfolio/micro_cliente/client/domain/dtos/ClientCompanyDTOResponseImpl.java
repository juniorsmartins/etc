package io.portfolio.micro_cliente.client.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import java.io.Serializable;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClientCompanyDTOResponseImpl
    (
        Long id,

        @NotBlank
        @Length(max = 100)
        String businessName,

        @Length(max = 100)
        String fantasyName,

        @NotBlank
        @CNPJ
        @Length(max = 20)
        String cnpj,

        LocalDate birthDate
    ) implements Serializable, PolicyDTO<Long>
{

}
