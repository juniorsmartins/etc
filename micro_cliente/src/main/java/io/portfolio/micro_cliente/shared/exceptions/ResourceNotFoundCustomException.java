package io.portfolio.micro_cliente.shared.exceptions;

import lombok.Data;

@Data
public final class ResourceNotFoundCustomException extends RuntimeException {
    public ResourceNotFoundCustomException(String message) {
        super(message);
    }
}

