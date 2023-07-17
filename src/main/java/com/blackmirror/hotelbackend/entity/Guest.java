package com.blackmirror.hotelbackend.entity;

import com.blackmirror.hotelbackend.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Guest{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    public long id;

    @Column(nullable = false,length = 100)
    public String name;

    @Column(nullable = false,length = 100)
    public String surName;

    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private Date birthDate;

    private long reservationId;
}
