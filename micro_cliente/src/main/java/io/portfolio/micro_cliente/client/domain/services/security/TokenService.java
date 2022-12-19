package io.portfolio.micro_cliente.client.domain.services.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.portfolio.micro_cliente.client.domain.entities.user.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String createToken(UserEntity userEntity) {
        try {
            var algorithm = Algorithm.HMAC256(this.secret);

            return JWT.create()
                    .withIssuer("micro_cliente") // identificação da aplicação
                    .withSubject(userEntity.getLogin())
                    .withClaim("id", userEntity.getId())
                    .withExpiresAt(expirationDate()) // definição de tempo de expiração do token
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error generating JWT token", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(this.secret);

            return JWT.require(algorithm)
                    .withIssuer("micro_cliente")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Invalid or expired JWT Token", exception);
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(22).toInstant(ZoneOffset.of("-03:00"));
    }
}
