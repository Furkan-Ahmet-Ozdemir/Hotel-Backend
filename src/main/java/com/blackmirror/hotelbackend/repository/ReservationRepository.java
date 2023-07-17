package com.blackmirror.hotelbackend.repository;

import com.blackmirror.hotelbackend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    public Long countById(Long id);
    public Optional<Reservation> getReservationByReservationCode(String id);
}
