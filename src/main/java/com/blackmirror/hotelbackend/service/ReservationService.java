package com.blackmirror.hotelbackend.service;

import com.blackmirror.hotelbackend.entity.Guest;
import com.blackmirror.hotelbackend.entity.Reservation;
import com.blackmirror.hotelbackend.entity.Room;
import com.blackmirror.hotelbackend.exception.GuestNotFoundException;
import com.blackmirror.hotelbackend.exception.ReservationNotFoundException;
import com.blackmirror.hotelbackend.repository.GuestRepository;
import com.blackmirror.hotelbackend.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.blackmirror.hotelbackend.utils.GetDateDayDifference.getDateDayDifference;

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
    public List<String> getFullRoomNumbers(Date checkInDate, Date checkOutDate){
        long daysBetween = getDateDayDifference(checkInDate, checkOutDate);
        Calendar c = Calendar.getInstance();

        List<String> roomList =  new ArrayList();
        Date dateToTravel = checkInDate;

        for(int i =0;i<daysBetween;i++){
            List<Reservation> reservationTravel = reservationRepository.getReservationInRange(dateToTravel);
            c.setTime(dateToTravel);
            c.add(Calendar.DATE, 1);
            dateToTravel = c.getTime();
            
            for(Reservation res: reservationTravel){
                List<Room> rooms = res.getRoomList();
                for (Room room:rooms)
                {roomList.add(room.getRoomNumber());}
            }

        }

        return roomList;
    }
}
