package com.blackmirror.hotelbackend.repository;

import com.blackmirror.hotelbackend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {

    @Query("SELECT r.roomNumber FROM Room r")
    List<String> getAllRoomNumbers();

    @Query("SELECT rt.id FROM Room r JOIN r.roomType rt WHERE r.roomNumber IN :roomNumbers GROUP BY rt.id")
    List<Object[]> findRoomTypeIdsByRoomNumbers(@Param("roomNumbers") List<String> roomNumbers);
}
