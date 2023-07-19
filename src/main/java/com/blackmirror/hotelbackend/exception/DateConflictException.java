package com.blackmirror.hotelbackend.exception;

public class DateConflictException extends RuntimeException{
    public DateConflictException(){
        super("Check given dates.");
    }
}
