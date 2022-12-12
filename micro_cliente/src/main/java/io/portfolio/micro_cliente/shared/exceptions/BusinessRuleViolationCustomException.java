package io.portfolio.micro_cliente.shared.exceptions;

import lombok.Data;

@Data
public final class BusinessRuleViolationCustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BusinessRuleViolationCustomException(String message) {
        super(message);
    }

    public BusinessRuleViolationCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
