package com.blackmirror.hotelbackend.controller;

import com.blackmirror.hotelbackend.entity.Room;
import com.blackmirror.hotelbackend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {

    @Autowired
    RoomService roomService;


    @PostMapping("/room/create")
    public Room createRoom(@RequestBody Room room){
        return roomService.createRoom(room);
    }

    @GetMapping("/room/getAll")
    public List<Room> getRooms(){
        return roomService.listAll();
    }
}
