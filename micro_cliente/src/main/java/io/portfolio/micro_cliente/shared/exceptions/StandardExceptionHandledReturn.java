package io.portfolio.micro_cliente.shared.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class StandardExceptionHandledReturn {

    private String status;
    private String fieldName;
    private String message;
    private String annotations;

    public StandardExceptionHandledReturn(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public StandardExceptionHandledReturn(String status, String message, String annotations, String fieldName) {
        this(status, message);
        this.annotations = annotations;
        this.fieldName = fieldName;
    }
}
