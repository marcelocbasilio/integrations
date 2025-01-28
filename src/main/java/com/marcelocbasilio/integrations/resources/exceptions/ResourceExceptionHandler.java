package com.marcelocbasilio.integrations.resources.exceptions;

import com.marcelocbasilio.integrations.services.exceptions.EmailException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<StandardError> handleEmailException(EmailException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError(status.getReasonPhrase()); // Email error
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}
