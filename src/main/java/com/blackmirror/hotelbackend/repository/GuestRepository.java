package com.blackmirror.hotelbackend.repository;

import com.blackmirror.hotelbackend.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest,Long> {
}
