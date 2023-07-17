package com.blackmirror.hotelbackend.controller;

import com.blackmirror.hotelbackend.entity.RoomType;
import com.blackmirror.hotelbackend.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomTypeController {

    @Autowired
    RoomTypeService roomTypeService;

    @PostMapping("/roomType/create")
    public RoomType createRoomType(@RequestBody RoomType roomType){
        return roomTypeService.createRoomType(roomType);
    }
    @GetMapping("/roomType/getAll")
    public List<RoomType> getAllRoomType(){
        return roomTypeService.listAll();
    }
}
