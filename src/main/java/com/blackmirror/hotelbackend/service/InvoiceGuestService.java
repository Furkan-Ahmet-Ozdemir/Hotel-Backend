package com.blackmirror.hotelbackend.service;

import com.blackmirror.hotelbackend.entity.InvoiceGuest;
import com.blackmirror.hotelbackend.exception.GuestNotFoundException;
import com.blackmirror.hotelbackend.exception.InvoiceGuestNotFoundException;
import com.blackmirror.hotelbackend.repository.InvoiceGuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceGuestService {

    @Autowired
    private InvoiceGuestRepository guestRepository;

    public List<InvoiceGuest> listAll(){
        return (List<InvoiceGuest>) guestRepository.findAll();
    }

    public InvoiceGuest save(InvoiceGuest user) {
        InvoiceGuest guest = guestRepository.save(user);
        return guest;
    }

    public InvoiceGuest get(Long id) throws InvoiceGuestNotFoundException {
        Optional<InvoiceGuest> result = guestRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new InvoiceGuestNotFoundException("Couldn find any InvoiceGuest with ID "+ id);
    }

    public void delete(Long id) throws InvoiceGuestNotFoundException {
        Long count = guestRepository.countById(id);
        if (count == null || count==0){
            throw new InvoiceGuestNotFoundException("Couldn find any InvoiceGuest with ID "+ id);
        }
        guestRepository.deleteById(id);
    }



}
