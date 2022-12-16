package io.portfolio.micro_cliente.shared.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Standard Exception Handled Return", description = "data transport structure for user response in case of error.")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record StandardExceptionHandledReturn
    (
        @Schema(name = "Status", description = "http response code", type = "String", example = "404 NOT_FOUND")
        String status,

        @Schema(name = "Annotations", description = "identification of the violated rule", type = "String", example = "@NotNull")
        String annotations,

        @Schema(name = "Field Name", description = "error source attribute name", type = "String", example = "id")
        String fieldName,

        @Schema(name = "Message", description = "explanation of the error situation", type = "String", example = "Resource not found in database")
        String message
    )
{
    public StandardExceptionHandledReturn(String status, String message) {
        this(status, null, null, message);
    }
}
