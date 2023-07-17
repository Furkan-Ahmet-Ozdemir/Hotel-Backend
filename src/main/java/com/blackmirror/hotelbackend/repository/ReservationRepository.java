package com.blackmirror.hotelbackend.repository;

import com.blackmirror.hotelbackend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    public Long countById(Long id);
    public Optional<Reservation> getReservationByReservationCode(String id);

    @Query("SELECT e FROM Reservation e WHERE e.checkInDate <= :dateToCheck AND e.checkOutDate > :dateToCheck")
    List<Reservation> getReservationInRange(Date dateToCheck);



}
