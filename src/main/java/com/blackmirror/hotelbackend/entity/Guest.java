package com.blackmirror.hotelbackend.entity;

import com.blackmirror.hotelbackend.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Guest extends Human{
    private Gender gender;
    private Date birthDate;
    private long reservationId;
}
