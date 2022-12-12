package io.portfolio.micro_cliente.shared.exceptions;

import lombok.Data;

@Data
public final class InternalErrorCustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InternalErrorCustomException(String message) {
        super(message);
    }

    public InternalErrorCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
