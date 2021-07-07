package org.example.exceptions.handler;


import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.example.exceptions.*;
import org.example.responses.Response;
import org.example.responses.ResponseMetadata;
import org.example.responses.StatusMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@Slf4j
public class globalExceptionHandler {

    @ExceptionHandler(AdminServiceException.class)
    public ResponseEntity<Response<?>> handleEmployeesServiceException(AdminServiceException e) {
        log.error(e.getMessage());
        return buildResponse(StatusMessage.UNKNOWN_INTERNAL_ERROR, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(locationServiceException.class)
    public ResponseEntity<Response<?>> handleEmployeesServiceException(locationServiceException e) {
        log.error(e.getMessage());
        return buildResponse(StatusMessage.UNKNOWN_INTERNAL_ERROR, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(menuServiceException.class)
    public ResponseEntity<Response<?>> handleEmployeesServiceException(menuServiceException e) {
        log.error(e.getMessage());
        return buildResponse(StatusMessage.UNKNOWN_INTERNAL_ERROR, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(orderServiceException.class)
    public ResponseEntity<Response<?>> handleEmployeesServiceException(orderServiceException e) {
        log.error(e.getMessage());
        return buildResponse(StatusMessage.UNKNOWN_INTERNAL_ERROR, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(reservationServiceException.class)
    public ResponseEntity<Response<?>> handleEmployeesServiceException(reservationServiceException e) {
        log.error(e.getMessage());
        return buildResponse(StatusMessage.UNKNOWN_INTERNAL_ERROR, INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Response<?>> buildResponse(StatusMessage statusMessage, HttpStatus status) {
        var response = Response.builder()
                .meta(ResponseMetadata.builder()
                        .statusMessage(statusMessage.name())
                        .statusCode(status.value())
                        .build())
                .build();
        return ResponseEntity.status(status)
                .body(response);
    }


}
