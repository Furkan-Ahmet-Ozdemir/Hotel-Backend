package com.blackmirror.hotelbackend.exception;

public class NullInvoiceException extends RuntimeException{
    public NullInvoiceException(){
        super("Invoice information cannot be null.");
    }
}
