package com.scarlet.backscarlet.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId ;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    ResponseEntity<StandardException> objectNotFoundException(ObjectNotFoundException e, ServletRequest request){
        StandardException error = new StandardException(LocalDateTime.now(ZoneId.of("UTC")), HttpStatus.NOT_FOUND.value()
                , e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
