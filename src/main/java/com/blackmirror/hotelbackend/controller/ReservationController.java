package com.blackmirror.hotelbackend.controller;

import com.blackmirror.hotelbackend.dto.ReservationCreateRequest;
import com.blackmirror.hotelbackend.dto.ReservationSearchRequest;
import com.blackmirror.hotelbackend.entity.Guest;
import com.blackmirror.hotelbackend.entity.Reservation;
import com.blackmirror.hotelbackend.entity.RoomType;
import com.blackmirror.hotelbackend.exception.GuestNotFoundException;
import com.blackmirror.hotelbackend.repository.GuestRepository;
import com.blackmirror.hotelbackend.service.GuestService;
import com.blackmirror.hotelbackend.service.ReservationService;
import com.blackmirror.hotelbackend.service.RoomService;
import com.blackmirror.hotelbackend.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.relation.RelationService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private GuestService guestService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping("/reservations")
    public List<Reservation> showReservations(Model model){
        List<Reservation> listUsers = reservationService.listAll();
        return listUsers;
    }

    @PostMapping("/reservations/save")
    public Reservation saveReservations(@RequestBody ReservationCreateRequest reservationRequest){
        ///** HANDLE RESERVATION CREATE **///
        Reservation reservation = new Reservation();
        reservation.setCheckInDate(reservationRequest.getCheckInDate());
        reservation.setCheckOutDate(reservationRequest.getCheckOutDate());
        reservation.setCustomerCount(reservationRequest.getCustomerCount());
        reservation.setGuestList(reservationRequest.getGuestList());
        reservation.setInvoiceGuest(reservationRequest.getInvoiceGuest());

        guestService.save(reservationRequest.getGuestList());
        Reservation reservationRes = reservationService.save(reservation);
        return reservationRes;
    }

   @PostMapping("/reservations/search")
    public List<RoomType> queryReservation(@RequestBody ReservationSearchRequest searchRequest){
        //** Only checked empty rooms didn't check customer count **/
       //** Move to another layer **/

        List<String> reservedRoomList = reservationService.getFullRoomNumbers(searchRequest.getCheckInDate(),searchRequest.getCheckOutDate());
        List<String> allRoomsList = roomService.getAllRoomNumbers();
        List<String> reservedRoomListDistinct = reservedRoomList.stream().distinct().collect(Collectors.toList());
        List<String> allRoomsListDistinct = allRoomsList.stream().distinct().collect(Collectors.toList());
        allRoomsListDistinct.removeAll(reservedRoomListDistinct);
        System.out.println("-------------------------");
        List<Object[]> availableRoomTypes = roomService.getGroupedRoomTypes(allRoomsListDistinct);
        List<Long> roomTypesToFetch = new ArrayList();
        for (int i=0;i<availableRoomTypes.size();i++){
            roomTypesToFetch.add((Long) availableRoomTypes.get(i)[0]);
        }
       System.out.println(roomTypesToFetch);
        List<RoomType> result = roomTypeService.getRoomTypeListByIdsGuestCount(roomTypesToFetch,searchRequest.getCustomerCount());
        return result;


   }
}
