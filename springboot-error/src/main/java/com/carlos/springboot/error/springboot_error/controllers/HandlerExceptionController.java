package com.carlos.springboot.error.springboot_error.controllers;

import com.carlos.springboot.error.springboot_error.exceptions.UserNotFoundException;
import com.carlos.springboot.error.springboot_error.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler({ ArithmeticException.class })
    public ResponseEntity<Error> divisionByZero(Exception ex) {
        Error error = new Error();
        error.setDate(new Date());
        error.setError("Error división por 0");
        error.setMessage(ex.getMessage());
        error.setStatutCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        //        return ResponseEntity.internalServerError().body(error);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }

    @ExceptionHandler({ NullPointerException.class, HttpMessageNotWritableException.class, UserNotFoundException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> numberFormatException(Exception ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("date", new Date());
        error.put("error", "Número invalido o incorrecto, no tiene formato de digito!");
        error.put("message", ex.getMessage());
        error.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return error;
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> userNotFoundException(Exception ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("date", new Date());
        error.put("error", "El usuario o el rol no existe!");
        error.put("message", ex.getMessage());
        error.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return error;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> notFoundException(NoHandlerFoundException ex) {
        Error error = new Error();
        error.setDate(new Date());
        error.setError("API rest no encontrado");
        error.setMessage(ex.getMessage());
        error.setStatutCode(HttpStatus.NOT_FOUND.value());
        //        return ResponseEntity.internalServerError().body(error);
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }
}
