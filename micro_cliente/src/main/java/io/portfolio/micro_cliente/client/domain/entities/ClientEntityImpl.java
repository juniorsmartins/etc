package io.portfolio.micro_cliente.client.domain.entities;

import io.portfolio.micro_cliente.client.domain.enums.GenreEnum;
import io.portfolio.micro_cliente.client.domain.enums.SexEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "clients")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
public final class ClientEntityImpl implements Serializable, PolicyEntity<Long> {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", length = 40)
    private String firstName;

    @Column(name = "last_name", length = 40)
    private String lastName;

    @Column(name = "cpf", length = 15, unique = true)
    private String cpf;

    @Column(name = "sex", length = 1)
    private SexEnum sex;

    @Column(name = "genre", length = 30)
    private GenreEnum genre;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "marital_status", length = 30)
    private String maritalStatus;

    @Column(name = "education", length = 200)
    private String education;
}
