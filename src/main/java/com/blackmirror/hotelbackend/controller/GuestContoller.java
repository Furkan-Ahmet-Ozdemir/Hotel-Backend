package com.blackmirror.hotelbackend.controller;

import com.blackmirror.hotelbackend.entity.Guest;
import com.blackmirror.hotelbackend.exception.GuestNotFoundException;
import com.blackmirror.hotelbackend.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
public class GuestContoller {
    @Autowired
    private GuestService guestService;

    @GetMapping("/guests")
    public List<Guest> showUserList(Model model){
        List<Guest> listUsers = guestService.listAll();
//        model.addAttribute("listUsers",listUsers);
        return listUsers;
    }

    @PostMapping("/guests/save")
    public Guest saveUser(@RequestBody Guest guest){
        Guest guest1 = guestService.save(guest);
        System.out.println("----------"+guest1+"----------------");
        return guest1;
    }

     //User Id si ile silme işlmei yapıyorum
    @GetMapping("/guests/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id,RedirectAttributes ra) throws GuestNotFoundException {

            guestService.delete(id);

        return "test";
    }



}
