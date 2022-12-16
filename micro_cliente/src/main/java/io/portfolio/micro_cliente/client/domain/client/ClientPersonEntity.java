package io.portfolio.micro_cliente.client.domain.client;

import io.portfolio.micro_cliente.client.application.rest.dtos_request.ClientPersonDTORequest;
import io.portfolio.micro_cliente.client.domain.enums.EducationEnum;
import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.MaritalStatusEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Table(name = "client_persons")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@PrimaryKeyJoinColumn(referencedColumnName="id")
public final class ClientPersonEntity extends Client implements PolicyEntity<Long> {

    @Column(name = "first_name", length = 30, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 30, nullable = false)
    private String lastName;

    @Column(name = "cpf", length = 15, nullable = false)
    private String cpf;

    @Column(name = "sex", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private SexEnum sex;

    @Column(name = "genre", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

    @Column(name = "marital_status", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private MaritalStatusEnum maritalStatus;

    @Column(name = "education", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private EducationEnum education;

    public ClientPersonEntity(ClientPersonDTORequest dto) {
        super(dto.id(), dto.birthDate(), new AddressEntity(dto.address()), new ContactEntity(dto.contact()));
        this.firstName = dto.firstName();
        this.lastName = dto.lastName();
        this.cpf = dto.cpf();
        this.sex = dto.sex();
        this.genre = dto.genre();
        this.maritalStatus = dto.maritalStatus();
        this.education = dto.education();
        log.info("Entity - person DTO converted entity.");
    }
}
