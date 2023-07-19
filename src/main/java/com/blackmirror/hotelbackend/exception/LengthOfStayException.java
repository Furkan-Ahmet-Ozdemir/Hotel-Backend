package com.blackmirror.hotelbackend.exception;

public class LengthOfStayException extends RuntimeException{
    public LengthOfStayException(){
        super("Select narrower date time period");
    }
}
