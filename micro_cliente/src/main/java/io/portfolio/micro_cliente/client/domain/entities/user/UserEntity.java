package io.portfolio.micro_cliente.client.domain.entities.user;

import io.portfolio.micro_cliente.client.application.rest.dtos_request.user.UserDTORequest;
import io.portfolio.micro_cliente.client.domain.entities.client.Client;
import jakarta.persistence.*;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public final class UserEntity implements Serializable, PolicyUserEntity<Long>, UserDetails {
    private static final long serialVersionUID = 1L;
    private static Logger log = LoggerFactory.getLogger(UserEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email_login", length = 150, unique = true, nullable = false)
    private String emailLogin;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    private Client client;

    public UserEntity(UserDTORequest dto) {
        this.emailLogin = dto.emailLogin();
        this.password = dto.password();
        log.info("user DTO converted entity.");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.emailLogin;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
