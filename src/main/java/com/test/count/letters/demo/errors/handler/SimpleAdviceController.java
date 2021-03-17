package com.test.count.letters.demo.errors.handler;

import com.test.count.letters.demo.errors.EmptyWordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class SimpleAdviceController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmptyWordException.class)
    public ResponseEntity<String> handleEmptyWordException(EmptyWordException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
