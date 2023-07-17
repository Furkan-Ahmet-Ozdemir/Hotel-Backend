package com.blackmirror.hotelbackend.repository;

import com.blackmirror.hotelbackend.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomTypeRepository extends JpaRepository<RoomType,Long> {

    public List<RoomType> findByIdInAndGuestLimitGreaterThanEqual(List<Long> ids, int guestCount);
}
