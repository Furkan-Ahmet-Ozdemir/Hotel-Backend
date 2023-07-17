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

    private String roomName;

    private long price;

    private int guestLimit;

    private String description;

    private String mainPicturePath;

    @OneToMany
    @JoinColumn(name = "roomPictureList")
    private List<RoomPicture> roomPictureList;

}
