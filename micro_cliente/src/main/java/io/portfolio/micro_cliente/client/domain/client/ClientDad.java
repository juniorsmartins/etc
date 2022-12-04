package io.portfolio.micro_cliente.client.domain.client;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public sealed abstract class ClientDad permits ClientPersonImpl, ClientCompanyImpl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "birth_date", nullable = false)
    protected LocalDate birthDate;
}
