package com.blackmirror.hotelbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<DefaultExceptionMessage> recordNotFoundException(RecordNotFoundException e) {
        DefaultExceptionMessage dex = new DefaultExceptionMessage();
        dex.setCode(HttpStatus.PRECONDITION_FAILED.value());
        dex.setMessage("NotFound");
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(dex);
    }
}
