package com.blackmirror.hotelbackend.repository;

import com.blackmirror.hotelbackend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
