package com.blackmirror.hotelbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private long id;


    //    @Column(nullable = false,length = 100)
    private String userName;

    //    @Column(nullable = false,length = 100)
    private String password;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "adminid" )
    private List<Token> tokens;
}
