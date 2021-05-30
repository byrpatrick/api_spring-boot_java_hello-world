package com.example.helloworld.message;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class CustomErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    Map<String, String> handleInternalServerError(Exception e) {
        Map<String, String> response = new HashMap<>();

        String errorStatus = "Internal server error occurred" + getExceptionMessage(e);
        response.put("message", errorStatus);

        return response;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    Map<String, String> handleAccessDeniedError(AccessDeniedException e) {
        Map<String, String> response = new HashMap<>();

        response.put("message", "Insufficent scopes.");

        return response;
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Not Found");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    private String getExceptionMessage(Exception e) {
        return Objects.isNull(e.getMessage()) ? "" : " cause: " + e.getMessage();
    }
}
