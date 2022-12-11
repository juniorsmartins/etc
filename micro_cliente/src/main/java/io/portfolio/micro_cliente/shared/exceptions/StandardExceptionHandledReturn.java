package io.portfolio.micro_cliente.shared.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class StandardExceptionHandledReturn {

    private String status;
    private String annotations;
    private String fieldName;
    private String message;
    private String cause;

    public StandardExceptionHandledReturn(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public StandardExceptionHandledReturn(String status, String message, Throwable cause) {
        this(status, message);
        this.cause = cause.toString();
    }

    public StandardExceptionHandledReturn(String status, String message, String annotations, String fieldName) {
        this(status, message);
        this.annotations = annotations;
        this.fieldName = fieldName;
    }
}
