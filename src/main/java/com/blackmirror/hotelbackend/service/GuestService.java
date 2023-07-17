package com.blackmirror.hotelbackend.service;

import com.blackmirror.hotelbackend.entity.Guest;
import com.blackmirror.hotelbackend.exception.GuestNotFoundException;
import com.blackmirror.hotelbackend.exception.RecordNotFoundException;
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

    public Guest get(Long id) throws GuestNotFoundException {
        Optional<Guest> result = guestRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new GuestNotFoundException("Couldn find any Guest with ID "+ id);
    }

    public void delete(Long id) throws GuestNotFoundException {
        Long count = guestRepository.countById(id);
        if (count == null || count==0){
            throw new RecordNotFoundException();
        }
            guestRepository.deleteById(id);
    }
}
