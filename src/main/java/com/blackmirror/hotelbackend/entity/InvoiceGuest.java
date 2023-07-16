package com.blackmirror.hotelbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class InvoiceGuest extends Human {
    private String tc;
    private String email;
    private String phoneNumber;

}
