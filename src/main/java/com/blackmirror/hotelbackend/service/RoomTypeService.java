package com.blackmirror.hotelbackend.service;

import com.blackmirror.hotelbackend.entity.RoomType;
import com.blackmirror.hotelbackend.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    public List<RoomType> listAll(){
        return roomTypeRepository.findAll();
    }

    public Optional<RoomType> getById(Long id){
        Optional<RoomType> roomType = roomTypeRepository.findById(id);
        return roomType;
    }

    public RoomType createRoomType(RoomType roomType){
        return roomTypeRepository.save(roomType);
    }

}
