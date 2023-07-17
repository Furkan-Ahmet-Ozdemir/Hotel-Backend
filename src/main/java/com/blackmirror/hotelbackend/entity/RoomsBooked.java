package com.blackmirror.hotelbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RoomsBooked {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private long id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "roomId")
    private Room room;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "reservationId")
    private Reservation reservation;


}
