package me.desoliture.urls_shortener.configuration;

import me.desoliture.urls_shortener.dto.SystemErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class SystemExceptionHandler {

    public static final String NO_TOKEN_EXCEPTION_MESSAGE = "No authorization token provided in required header 'Authorization'";

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<SystemErrorResponse> handleIllegalArgument(Exception e) {
        var body = new SystemErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(body);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<SystemErrorResponse> handleNoAuth() {
        var body = new SystemErrorResponse(NO_TOKEN_EXCEPTION_MESSAGE, HttpStatus.UNAUTHORIZED);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(body);
    }

    @ExceptionHandler(BadCredentialsSystemException.class)
    public ResponseEntity<SystemErrorResponse> handleAuth(BadCredentialsSystemException e) {
        var body = new SystemErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(body);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<SystemErrorResponse> handleNoSuchElement(NoSuchElementException e) {
        var body = new SystemErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(body);
    }
}
