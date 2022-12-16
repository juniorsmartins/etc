package io.portfolio.micro_cliente.client.domain.entities.user;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public final class UserEntity implements Serializable, PolicyUserEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", length = 100, nullable = false)
    private String login;

    @Column(name = "password", length = 250, nullable = false)
    private String password;
}
