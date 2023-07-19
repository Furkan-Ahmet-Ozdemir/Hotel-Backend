package com.blackmirror.hotelbackend.exception;

public class DateFormatException extends RuntimeException{
    public DateFormatException(){
        super("Check given date format is not correct.");
    }
}
