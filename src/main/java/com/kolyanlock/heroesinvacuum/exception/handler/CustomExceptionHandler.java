package com.kolyanlock.heroesinvacuum.exception.handler;

import com.kolyanlock.heroesinvacuum.exception.CustomEntityExistsException;
import com.kolyanlock.heroesinvacuum.exception.CustomEntityNotFoundException;
import com.kolyanlock.heroesinvacuum.exception.ErrorBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomExceptionHandler {

    private ResponseEntity<ErrorBody> getResponseEntity(HttpServletRequest request, HttpStatus httpStatus, RuntimeException ex) {
        ErrorBody body = new ErrorBody();
        body.setTimestamp(LocalDateTime.now());
        body.setStatus(httpStatus.value());
        body.setError(httpStatus.getReasonPhrase());
        body.setException(ex.getClass().getName());
        body.setMessage(ex.getMessage());
        body.setPath(request.getRequestURI());

        return new ResponseEntity<>(body, httpStatus);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorBody> handleCustomEntityExistsException(CustomEntityExistsException ex, HttpServletRequest request) {
        return getResponseEntity(request, HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorBody> handleCustomEntityNotFoundException(CustomEntityNotFoundException ex, HttpServletRequest request) {
        return getResponseEntity(request, HttpStatus.NOT_FOUND, ex);
    }
}
