package io.portfolio.micro_cliente.client.domain.services.security.user;

import io.portfolio.micro_cliente.client.application.rest.dtos_request.user.PolicyUserDTORequest;
import org.springframework.stereotype.Service;

@Service
public sealed interface PolicyUserService<U extends PolicyUserDTORequest<ID>, ID> permits AuthenticationService { }
