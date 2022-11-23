package io.portfolio.micro_cliente.shared.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class ExceptionHandling {

    @Autowired
    private MessageSource internationalMessage;

    @ExceptionHandler(ResourceNotFoundCustomException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<StandardExceptionHandledReturn> methodStandardExceptionHandledReturn(
            ResourceNotFoundCustomException resourceNotFoundCustomException) {
        return ResponseEntity
                .badRequest()
                .body(new StandardExceptionHandledReturn(HttpStatus.NOT_FOUND.toString(),
                        resourceNotFoundCustomException.getMessage()));
    }

}

