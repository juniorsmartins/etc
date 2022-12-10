package io.portfolio.micro_cliente.client.domain.client;

import io.portfolio.micro_cliente.client.domain.dtos.ContactDTORequest;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "contacts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class ContactEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "cell", length = 20)
    private String cell;

    @OneToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    private Client client;

    public ContactEntity(ContactDTORequest dto) {
        this.id = dto.id();
        this.email = dto.email();
        this.cell = dto.cell();
    }
}
