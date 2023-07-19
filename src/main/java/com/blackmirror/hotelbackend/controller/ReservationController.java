package com.blackmirror.hotelbackend.controller;

import com.blackmirror.hotelbackend.dto.ReservationCreateRequest;
import com.blackmirror.hotelbackend.dto.ReservationSearchRequest;
import com.blackmirror.hotelbackend.entity.*;
import com.blackmirror.hotelbackend.exception.GuestNotFoundException;
import com.blackmirror.hotelbackend.repository.GuestRepository;
import com.blackmirror.hotelbackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.relation.RelationService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.blackmirror.hotelbackend.utils.GeneratePNR.generateUniquePNR;


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

    @Autowired
    private InvoiceGuestService invoiceGuestService;

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
        String PNR = generateUniquePNR();
        reservation.setReservationCode(PNR);
        // HANDLE GUESTS
        // reservation.setGuestList(reservationRequest.getGuestList());
        // guestService.save(reservationRequest.getGuestList());
        List<Guest> guestList =guestService.listAll();
        reservation.setGuestList(guestList);
        ///HANDLE GUESTS ABOVE IS JUST PLACEHOLDER
        reservation.setInvoiceGuest(reservationRequest.getInvoiceGuest());
        List<Room> roomListToReservation =new ArrayList<>();
        List<Room> roomListAssignable = availableRoomsToAssignRoomtype(reservationRequest.getCheckInDate(),
                reservationRequest.getCheckOutDate(),reservationRequest.getRoomTypeId());
        roomListToReservation.add(roomListAssignable.get(0));

        reservation.setRoomList(roomListToReservation);


        invoiceGuestService.save(reservation.getInvoiceGuest());
        Reservation reservationRes = reservationService.save(reservation);
        return reservationRes;
    }

   @PostMapping("/reservations/search")
    public List<RoomType> queryReservation(@RequestBody ReservationSearchRequest searchRequest){
       //** May move to another layer **/

        List<String> allRoomsListDistinct = getAvailableRooms(searchRequest.getCheckInDate(),searchRequest.getCheckOutDate());

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

    public List<String> getAvailableRooms(Date date1, Date date2){
        List<String> reservedRoomList = reservationService.getFullRoomNumbers(date1,date2);
        List<String> allRoomsList = roomService.getAllRoomNumbers();
        List<String> reservedRoomListDistinct = reservedRoomList.stream().distinct().collect(Collectors.toList());
        List<String> allRoomsListDistinct = allRoomsList.stream().distinct().collect(Collectors.toList());
        allRoomsListDistinct.removeAll(reservedRoomListDistinct);
        return allRoomsListDistinct;
    }
    public List<Room> availableRoomsToAssignRoomtype(Date date1,Date date2,Long roomType){
        List<Room> matchingRoom = new ArrayList<>();
        List<String> roomNumbers = getAvailableRooms(date1,date2);




        for (String roomNumber : roomNumbers) {
            Room room = roomService.getRoomByNumberAndTypeId(roomNumber, roomType);
            if (room != null) {
                matchingRoom.add(room);
            }
        }




        return matchingRoom;
    }
}
