package com.blackmirror.hotelbackend.service;

import com.blackmirror.hotelbackend.entity.Guest;
import com.blackmirror.hotelbackend.entity.Reservation;
import com.blackmirror.hotelbackend.exception.GuestNotFoundException;
import com.blackmirror.hotelbackend.exception.ReservationNotFoundException;
import com.blackmirror.hotelbackend.repository.GuestRepository;
import com.blackmirror.hotelbackend.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> listAll(){
        return reservationRepository.findAll();
    }

    public Reservation save(Reservation user) {
        Reservation guest = reservationRepository.save(user);
        return guest;
    }

    public Reservation get(Long id) throws ReservationNotFoundException {
        Optional<Reservation> result = reservationRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new ReservationNotFoundException();
    }

    public void delete(Long id) throws GuestNotFoundException {
        Long count = reservationRepository.countById(id);
        if (count == null || count==0){
            throw new GuestNotFoundException();
        }
        reservationRepository.deleteById(id);
    }

    public Reservation getByPNR(String PNR) throws ReservationNotFoundException {
        Optional<Reservation> result = reservationRepository.getReservationByReservationCode(PNR);
        if (result.isPresent()){
            return result.get();
        }
        throw new ReservationNotFoundException();
    }
}
