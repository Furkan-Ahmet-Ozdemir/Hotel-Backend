package com.blackmirror.hotelbackend.exception;

public class GuestNotFoundException extends RuntimeException {
    public GuestNotFoundException() {
            super("Guest not found!!");
    }
}
