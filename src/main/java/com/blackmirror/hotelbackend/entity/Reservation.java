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
    private int id;
    private Date checkInDate;
    private Date checkOutDate;
    private long totalPrice;
    private long perDayPrice;
    private int lenghtOfStay;
    private int customerCount;

    @OneToMany
    @JoinColumn(name = "guestId")
    private List<Guest> guestList;

    @OneToMany
    @JoinColumn(name = "roomList")
    private List<Room> roomList;

    private boolean status = false;

}
