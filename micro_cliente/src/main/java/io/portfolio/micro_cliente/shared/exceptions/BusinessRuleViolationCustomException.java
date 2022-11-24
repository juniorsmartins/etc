package io.portfolio.micro_cliente.shared.exceptions;

import lombok.Data;

@Data
public final class BusinessRuleViolationCustomException extends RuntimeException {
    public BusinessRuleViolationCustomException(String message) {
        super(message);
    }
}
