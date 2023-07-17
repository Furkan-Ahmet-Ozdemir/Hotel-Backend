package com.blackmirror.hotelbackend.controller;

import com.blackmirror.hotelbackend.entity.Guest;
import com.blackmirror.hotelbackend.entity.Reservation;
import com.blackmirror.hotelbackend.exception.GuestNotFoundException;
import com.blackmirror.hotelbackend.repository.GuestRepository;
import com.blackmirror.hotelbackend.service.GuestService;
import com.blackmirror.hotelbackend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.relation.RelationService;
import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private GuestService guestService;

    @GetMapping("/reservations")
    public List<Reservation> showReservations(Model model){
        List<Reservation> listUsers = reservationService.listAll();
        return listUsers;
    }

    @PostMapping("/reservations/save")
    public Reservation saveReservations(@RequestBody Reservation reservation1){
        guestService.save(reservation1.getGuestList());
        Reservation reservation = reservationService.save(reservation1);
        return reservation;
    }


}
