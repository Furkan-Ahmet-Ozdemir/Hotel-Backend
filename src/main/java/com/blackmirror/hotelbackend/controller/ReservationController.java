package com.blackmirror.hotelbackend.controller;

import com.blackmirror.hotelbackend.dto.ReservationCreateRequest;
import com.blackmirror.hotelbackend.dto.ReservationSearchRequest;
import com.blackmirror.hotelbackend.entity.*;
import com.blackmirror.hotelbackend.exception.*;
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
import static com.blackmirror.hotelbackend.utils.GetDateDayDifference.getDateDayDifference;


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
    private EmailSenderService senderService;

    @Autowired
    private InvoiceGuestService invoiceGuestService;

    @GetMapping("/reservations")
    public List<Reservation> showReservations(){
        List<Reservation> listUsers = reservationService.listAll();
        return listUsers;
    }

    @PostMapping("/reservations/save")
    public Reservation saveReservations(@RequestBody ReservationCreateRequest reservationRequest){

        long lengthOfStay = getDateDayDifference(reservationRequest.getCheckInDate(),reservationRequest.getCheckOutDate());
        int intValuelengthOfStay=0;


        if(reservationRequest.getInvoiceGuest()==null)
            throw new NullInvoiceException();

        int dateCompRes = reservationRequest.getCheckInDate().compareTo(reservationRequest.getCheckOutDate());
        if(dateCompRes > 0)
            throw new DateConflictException();


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
        if(roomListAssignable.size()==0)
            throw new NoAvailableRoomException();
        Room roomToAssign =roomListAssignable.get(0);
        roomListToReservation.add(roomToAssign);

        reservation.setRoomList(roomListToReservation);

        try {
            intValuelengthOfStay = Math.toIntExact(lengthOfStay);
        } catch (ArithmeticException e) {
            throw new LengthOfStayException();
        }

        reservation.setLenghtOfStay((int) lengthOfStay);
        long roomPrice = roomToAssign.getRoomType().getPrice();
        reservation.setPerDayPrice(roomPrice);
        reservation.setTotalPrice(roomPrice*lengthOfStay);

        invoiceGuestService.save(reservation.getInvoiceGuest());

        Reservation reservationRes = reservationService.save(reservation);

        RoomsBooked roomsBooked = new RoomsBooked();

        sendMail(/*reservation.getInvoiceGuest().getEmail()*/"furkan844.faz@gmail.com",  "Reservation Code: "+reservation.getReservationCode()
                ,"Rezervasyon kodunuz ile rezervasyonu inceleyebilir ve isterseniz sitemiz üzerinden iptal edebilirsiniz");

        return reservationRes;
    }

   @PostMapping("/reservations/search")
    public List<RoomType> queryReservation(@RequestBody ReservationSearchRequest searchRequest){
       //** May move to another layer **/
        int dateCompRes = searchRequest.getCheckInDate().compareTo(searchRequest.getCheckOutDate());
        if(dateCompRes > 0)
            throw new DateConflictException();

        List<String> allRoomsListDistinct = getAvailableRooms(searchRequest.getCheckInDate(),searchRequest.getCheckOutDate());

        System.out.println("-------------------------");
        List<Object[]> availableRoomTypes = roomService.getGroupedRoomTypes(allRoomsListDistinct);
        List<Long> roomTypesToFetch = new ArrayList();
        for (int i=0;i<availableRoomTypes.size();i++){
            roomTypesToFetch.add((Long) availableRoomTypes.get(i)[0]);
        }

        //System.out.println(roomTypesToFetch);
        List<RoomType> result = roomTypeService.getRoomTypeListByIdsGuestCount(roomTypesToFetch,searchRequest.getCustomerCount());
       if(result.size()==0)
           throw new NoAvailableRoomException();
        return result;


   }
    @GetMapping(value = "/getByPNR/{id}")
    public Reservation getById(@PathVariable String id) {
        Reservation res= reservationService.getByPNR(id);

        return reservationService.getByPNR(id);
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

    public void sendMail(String toEmail,String subject,String body) {
//        senderService.sendSimpleEmail("ahmetsoy1903@gmail.com",
//                "This is email subject",
//                "This is email  body");
        senderService.sendSimpleEmail(toEmail,
                subject,
                body);
    }

}
