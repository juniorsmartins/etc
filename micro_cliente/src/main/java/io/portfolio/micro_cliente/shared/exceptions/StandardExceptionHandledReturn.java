package io.portfolio.micro_cliente.shared.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Standard Exception Handled Return", description = "data transport structure for user response in case of error.")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class StandardExceptionHandledReturn {

    @Schema(name = "Status", description = "http response code", type = "String", example = "404 NOT_FOUND")
    private String status;

    @Schema(name = "Annotations", description = "identification of the violated rule", type = "String", example = "@NotNull")
    private String annotations;

    @Schema(name = "Field Name", description = "error source attribute name", type = "String", example = "id")
    private String fieldName;

    @Schema(name = "Message", description = "explanation of the error situation", type = "String", example = "Resource not found in database")
    private String message;

    @Schema(name = "Cause", description = "detail about the source of the error", type = "String")
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

    public StandardExceptionHandledReturn(String status, String message, String annotations, String fieldName, Throwable cause) {
        this(status, message, annotations, fieldName);
        this.cause = cause.toString();
    }
}
