package io.portfolio.micro_cliente.client.domain.client;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long id;

    @Column(name = "birth_date")
    protected LocalDate birthDate;

    @OneToOne(mappedBy = "client", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER, targetEntity = AddressEntity.class)
    private AddressEntity address;

    @OneToOne(mappedBy = "client", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER, targetEntity = ContactEntity.class)
    private ContactEntity contact;
}
