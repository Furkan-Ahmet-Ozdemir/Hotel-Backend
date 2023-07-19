package com.blackmirror.hotelbackend.exception;

public class RequesFormatException extends RuntimeException{

    public RequesFormatException(){
        super("Request is not in expected form.");
    }
}
