package io.portfolio.micro_cliente.client.application.rest.controllers;

import io.portfolio.micro_cliente.client.application.rest.dtos_request.user.UserDTORequest;
import io.portfolio.micro_cliente.client.domain.entities.user.UserEntity;
import io.portfolio.micro_cliente.client.domain.services.security.TokenJWTDTO;
import io.portfolio.micro_cliente.client.domain.services.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenJWTDTO> efetuarLogin(@RequestBody @Valid UserDTORequest userDTORequest) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(userDTORequest.login(), userDTORequest.password());
        var authentication = this.authenticationManager.authenticate(authenticationToken);
        var tokenJWT = this.tokenService.createToken((UserEntity) authentication.getPrincipal());

        return ResponseEntity
                .ok(new TokenJWTDTO(tokenJWT));
    }
}

