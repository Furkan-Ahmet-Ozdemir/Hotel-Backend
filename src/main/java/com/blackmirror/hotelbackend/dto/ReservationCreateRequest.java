package com.blackmirror.hotelbackend.dto;

import com.blackmirror.hotelbackend.entity.Guest;
import com.blackmirror.hotelbackend.entity.InvoiceGuest;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ReservationCreateRequest {

    private Date checkInDate;
    private Date checkOutDate;
    private int customerCount;
    private long roomTypeId;
    //* Guest list 	automaticly assigned there is no guest section in front end *//
    //private List<Guest> guestList;

    private InvoiceGuest invoiceGuest;
}
