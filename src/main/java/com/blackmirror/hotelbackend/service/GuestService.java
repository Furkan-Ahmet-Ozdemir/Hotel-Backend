package com.blackmirror.hotelbackend.service;

import com.blackmirror.hotelbackend.entity.Guest;
import com.blackmirror.hotelbackend.exception.NotFoundException;
import com.blackmirror.hotelbackend.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService {
    @Autowired
    private GuestRepository guestRepository;

    public List<Guest> listAll(){
        return (List<Guest>) guestRepository.findAll();
    }

    public Guest save(Guest user) {
       Guest guest = guestRepository.save(user);
        return guest;
    }

    public Guest get(Long id) throws NotFoundException {
        Optional<Guest> result = guestRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new NotFoundException("Couldn find any User with ID "+ id);
    }
}
