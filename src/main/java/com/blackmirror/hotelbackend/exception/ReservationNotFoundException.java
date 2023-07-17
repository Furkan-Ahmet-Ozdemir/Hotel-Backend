package com.blackmirror.hotelbackend.exception;

public class ReservationNotFoundException extends RuntimeException{
    public ReservationNotFoundException() {
        super("Reservation not found!!    '''");
    }
}
