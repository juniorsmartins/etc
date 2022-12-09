package io.portfolio.micro_cliente.client.domain.client.address;

import io.portfolio.micro_cliente.client.domain.client.Client;
import io.portfolio.micro_cliente.client.domain.dtos.address.AddressDTORequest;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "addresses")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class AddressEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_client")
    private Long idClient;

    @Column(name = "cep", length = 15, nullable = false)
    private String cep;

    @Column(name = "state", length = 100, nullable = false)
    private String state;

    @Column(name = "city", length = 100, nullable = false)
    private String city;

    @Column(name = "district", length = 100, nullable = false)
    private String district;

    @Column(name = "public_place", length = 100, nullable = false)
    private String publicPlace;

    @Column(name = "house_number", nullable = true)
    private int houseNumber;

    @Column(name = "complement", length = 250, nullable = true)
    private String complement;

    @MapsId("id_client")
    @OneToOne(targetEntity = Client.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    private Client client;

    public AddressEntity(AddressDTORequest dto) {
        this.cep = dto.cep();
        this.state = dto.state();
        this.city = dto.city();
        this.district = dto.district();
        this.publicPlace = dto.publicPlace();
        this.houseNumber = dto.houseNumber();
        this.complement = dto.complement();
    }
}

