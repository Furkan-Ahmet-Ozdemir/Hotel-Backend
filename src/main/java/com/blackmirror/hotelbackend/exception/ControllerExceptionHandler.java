package com.blackmirror.hotelbackend.exception;

import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

//    @ResponseBody
//    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
//    @ExceptionHandler(RecordNotFoundException.class)
//    public ResponseEntity<DefaultExceptionMessage> recordNotFoundException(RecordNotFoundException e) {
//        DefaultExceptionMessage dex = new DefaultExceptionMessage();
//        dex.setCode(HttpStatus.PRECONDITION_FAILED.value());
//        dex.setMessage("Record Not Found");
//        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(dex);
//    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(GuestNotFoundException.class)
    public ResponseEntity<DefaultExceptionMessage> guestNotFoundException(GuestNotFoundException e) {
        DefaultExceptionMessage dex = new DefaultExceptionMessage();
        dex.setCode(HttpStatus.PRECONDITION_FAILED.value());
        dex.setMessage("Guest Not Found");
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(dex);
    }


    @ResponseBody
    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<DefaultExceptionMessage> reservationNotFoundException(ReservationNotFoundException e) {
        DefaultExceptionMessage dex = new DefaultExceptionMessage();
        dex.setCode(HttpStatus.PRECONDITION_FAILED.value());
        dex.setCode(404);
        dex.setMessage("Reservation Not Found");
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(dex);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(NoAvailableRoomException.class)
    public ResponseEntity<DefaultExceptionMessage> noAvailableRoomException(NoAvailableRoomException e) {
        DefaultExceptionMessage dex = new DefaultExceptionMessage();
        dex.setCode(HttpStatus.EXPECTATION_FAILED.value());
        dex.setMessage("There is no available room.");
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(dex);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(RequesFormatException.class)
    public ResponseEntity<DefaultExceptionMessage> requestFormatException(RequesFormatException e) {
        DefaultExceptionMessage dex = new DefaultExceptionMessage();
        dex.setCode(HttpStatus.PRECONDITION_FAILED.value());
        dex.setCode(405);
        dex.setMessage("Request is not in expected format.");
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(dex);
    }


    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateConflictException.class)
    public ResponseEntity<DefaultExceptionMessage> dateConflictException(DateConflictException e) {
        DefaultExceptionMessage dex = new DefaultExceptionMessage();
        dex.setCode(HttpStatus.BAD_REQUEST.value());
        dex.setMessage("Check given dates.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dex);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DefaultExceptionMessage> dataParseException(HttpMessageNotReadableException e) {
        DefaultExceptionMessage dex = new DefaultExceptionMessage();
        dex.setCode(HttpStatus.BAD_REQUEST.value());
        dex.setMessage("Data parse exception.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dex);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullInvoiceException.class)
    public ResponseEntity<DefaultExceptionMessage> nullInvoiceExceptionHandler(NullInvoiceException e) {
        DefaultExceptionMessage dex = new DefaultExceptionMessage();
        dex.setCode(HttpStatus.BAD_REQUEST.value());
        dex.setMessage("Invoice information cannot be null.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dex);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LengthOfStayException.class)
    public ResponseEntity<DefaultExceptionMessage> nullInvoiceExceptionHandler(LengthOfStayException e) {
        DefaultExceptionMessage dex = new DefaultExceptionMessage();
        dex.setCode(HttpStatus.BAD_REQUEST.value());
        dex.setMessage("Select narrower date time period");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dex);
    }





}
