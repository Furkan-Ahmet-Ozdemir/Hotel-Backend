package com.blackmirror.hotelbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date checkInDate;
    private Date checkOutDate;
    private long totalPrice;
    private long perDayPrice;
    private int lenghtOfStay;
    private int customerCount;

    private String name;
    private String surName;
    private String phoneNumber;
    private String tc;
    private String email;

    @OneToMany
    @JoinColumn(name = "guestList")
    private List<Guest> guestList;

    @ManyToOne
    private Room room;

    @OneToOne
    @JoinColumn(name = "invoiceGuestId")
    private InvoiceGuest invoiceGuest;

    private boolean status = true;

    private String reservationCode;

}
