package com.blackmirror.hotelbackend.repository;

import com.blackmirror.hotelbackend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
