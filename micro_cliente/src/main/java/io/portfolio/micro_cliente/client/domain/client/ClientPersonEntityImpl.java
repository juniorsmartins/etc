package io.portfolio.micro_cliente.client.domain.client;

import io.portfolio.micro_cliente.client.domain.dtos.ClientPersonDTORequestImpl;
import io.portfolio.micro_cliente.client.domain.enums.EducationEnum;
import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.MaritalStatusEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client_persons")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class ClientPersonEntityImpl extends Client implements PolicyEntity<Long> {

    @Column(name = "first_name", length = 30, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 30, nullable = false)
    private String lastName;

    @Column(name = "cpf", length = 15, nullable = false, unique = true)
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

    public ClientPersonEntityImpl(ClientPersonDTORequestImpl dto) {
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
