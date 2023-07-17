package com.blackmirror.hotelbackend.service;

import com.blackmirror.hotelbackend.entity.Guest;
import com.blackmirror.hotelbackend.entity.Room;
import com.blackmirror.hotelbackend.repository.RoomRepository;
import com.blackmirror.hotelbackend.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> listAll(){
        return  roomRepository.findAll();
    }

    public Room createRoom(Room roomToCreate){
        return roomRepository.save(roomToCreate);
    }

    public List<String> getAllRoomNumbers(){
        return roomRepository.getAllRoomNumbers();
    }

    public List<Object[]> getGroupedRoomTypes(List<String> roomNumberList){
        return roomRepository.findRoomTypeIdsByRoomNumbers(roomNumberList);
    }

}
