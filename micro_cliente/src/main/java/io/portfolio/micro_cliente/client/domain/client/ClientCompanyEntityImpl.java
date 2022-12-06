package io.portfolio.micro_cliente.client.domain.client;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "client_companys")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@PrimaryKeyJoinColumn(referencedColumnName="id")
public final class ClientCompanyEntityImpl extends Client implements PolicyEntity<Long>  {

    @Column(name = "business_name", length = 100, nullable = false)
    private String businessName;

    @Column(name = "fantasy_name", length = 100, nullable = true)
    private String fantasyName;

    @Column(name = "cnpj", length = 20, nullable = false, unique = true)
    private String cnpj;
}
