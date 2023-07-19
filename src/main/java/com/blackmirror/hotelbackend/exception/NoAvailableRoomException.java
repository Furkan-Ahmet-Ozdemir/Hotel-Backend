package com.blackmirror.hotelbackend.exception;

public class NoAvailableRoomException extends RuntimeException{
    public NoAvailableRoomException() {
        super("No available room.");
    }
}
