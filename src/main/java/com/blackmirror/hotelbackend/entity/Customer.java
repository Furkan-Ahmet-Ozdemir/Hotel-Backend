package com.blackmirror.hotelbackend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Customer extends Human {
    private String tc;
    private String email;
    private String phoneNumber;

}
