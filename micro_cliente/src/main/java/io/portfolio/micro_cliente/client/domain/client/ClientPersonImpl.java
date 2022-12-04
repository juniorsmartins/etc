package io.portfolio.micro_cliente.client.domain.client;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "client_persons")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class ClientPersonImpl extends ClientDad implements Serializable, PolicyEntity<Long>  {
    private static final long serialVersionUID = 1L;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "fantasy_name")
    private String fantasyName;

    @Column(name = "cnpj", unique = true)
    private String cnpj;
}
