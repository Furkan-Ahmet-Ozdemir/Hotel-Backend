package com.blackmirror.hotelbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ContactForm {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    public long id;

    @Column(nullable = false,length = 100)
    public String name;

    @Column(nullable = false,length = 100)
    public String surName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false,length = 13)
    private String phoneNumber;

    @Column(nullable = false)
    private String message;
}
