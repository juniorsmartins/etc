package io.portfolio.micro_cliente.client.domain.client;

import io.portfolio.micro_cliente.client.domain.dtos.AddressDTORequest;
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
public final class AddressEntity implements Serializable, PolicyEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cep", length = 15, nullable = false)
    private String cep;

    @Column(name = "state", length = 2, nullable = false)
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

//    @MapsId("id_client")
    @OneToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    private Client client;

    public AddressEntity(AddressDTORequest dto) {
        this.id = dto.id();
        this.cep = dto.cep();
        this.state = dto.state();
        this.city = dto.city();
        this.district = dto.district();
        this.publicPlace = dto.publicPlace();
        this.houseNumber = dto.houseNumber();
        this.complement = dto.complement();
    }
}

