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
    public String name;
    public String surName;
    private Gender gender;
    private Date birthDate;
    private long reservationId;
}
