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
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    public long id;

    @Column(nullable = false, length = 50)
    private String roomName;

    @Column(nullable = false, length = 10)
    private long price;

    @Column(nullable = false, length = 2)
    private int guestLimit;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private String mainPicturePath;

    @OneToMany
    @JoinColumn(name = "roomPictureList")
    private List<RoomPicture> roomPictureList;

}
