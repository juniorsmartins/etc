package io.portfolio.micro_cliente.client.domain.entities.client;

import io.portfolio.micro_cliente.client.application.rest.dtos_request.client.ContactDTORequest;
import io.portfolio.micro_cliente.client.domain.services.security.AuthenticationService;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static Logger log = LoggerFactory.getLogger(ContactEntity.class);

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
        log.info("contact DTO converted entity.");
    }
}
