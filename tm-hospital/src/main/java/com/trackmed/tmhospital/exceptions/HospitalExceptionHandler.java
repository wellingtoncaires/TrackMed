package com.trackmed.tmhospital.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class HospitalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HospitalException.class)
    public ResponseEntity<Object> standardError(HospitalException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setThrowable(e.getCause());

        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(CommunicationMicroServiceException.class)
    public ResponseEntity<Object> standardError(CommunicationMicroServiceException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.resolve(e.getStatus());

        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setThrowable(e.getCause());

        return new ResponseEntity<>(error, status);
    }
}
