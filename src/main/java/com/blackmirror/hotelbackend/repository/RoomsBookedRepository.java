package com.blackmirror.hotelbackend.repository;

import com.blackmirror.hotelbackend.entity.RoomsBooked;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomsBookedRepository extends JpaRepository<RoomsBooked,Long> {
}
