package io.portfolio.micro_cliente.client.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.portfolio.micro_cliente.client.domain.client.ClientCompanyEntityImpl;

import java.io.Serializable;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClientCompanyDTOResponseImpl
    (
        @JsonProperty("Identifier")
        Long id,

        String businessName,
        String fantasyName,

        @JsonProperty("National Register of Legal Entities")
        String cnpj,

        @JsonIgnore
        LocalDate birthDate
    ) implements Serializable, PolicyDTO<Long>
{
    public ClientCompanyDTOResponseImpl(ClientCompanyEntityImpl companyEntity) {
        this(companyEntity.getId(),
            companyEntity.getBusinessName(),
            companyEntity.getFantasyName(),
            companyEntity.getCnpj(),
            companyEntity.getBirthDate());
    }
}
