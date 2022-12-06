package io.portfolio.micro_cliente.client.domain.client;

import io.portfolio.micro_cliente.client.domain.dtos.ClientPersonDTORequestImpl;
import io.portfolio.micro_cliente.client.domain.enums.EducationEnum;
import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.MaritalStatusEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "client_persons")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class ClientPersonImpl extends Client implements Serializable, PolicyEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private SexEnum sex;

    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

    @Column(name = "marital_status")
    @Enumerated(EnumType.STRING)
    private MaritalStatusEnum maritalStatus;

    @Column(name = "education")
    @Enumerated(EnumType.STRING)
    private EducationEnum education;

    public ClientPersonImpl(ClientPersonDTORequestImpl dto) {
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.cpf = dto.getCpf();
        this.sex = dto.getSex();
        this.genre = dto.getGenre();
        this.maritalStatus = dto.getMaritalStatus();
        this.education = dto.getEducation();
        super.birthDate = dto.getBirthDate();
    }
}
