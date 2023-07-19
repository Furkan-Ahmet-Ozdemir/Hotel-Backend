package com.blackmirror.hotelbackend.exception;

public class InvoiceGuestNotFoundException extends Throwable {
    public InvoiceGuestNotFoundException(String s) {
        super("Invoice guest not found.");
    }
}
