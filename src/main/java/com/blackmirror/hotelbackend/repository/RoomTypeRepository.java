package com.blackmirror.hotelbackend.repository;

import com.blackmirror.hotelbackend.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomType,Long> {
}
