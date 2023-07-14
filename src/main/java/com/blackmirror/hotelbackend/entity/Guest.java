package com.blackmirror.hotelbackend.entity;

import com.blackmirror.hotelbackend.enums.Gender;

import java.util.Date;

public class Guest extends Human{
    private Gender gender;
    private Date birthDate;
    private long reservationId;
}
