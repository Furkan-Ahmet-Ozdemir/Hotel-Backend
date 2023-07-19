package com.blackmirror.hotelbackend.service;

import com.blackmirror.hotelbackend.entity.ContactForm;
import com.blackmirror.hotelbackend.entity.Guest;
import com.blackmirror.hotelbackend.repository.ContactFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactFormService {

    @Autowired
    private ContactFormRepository contactFormRepository;

    public ContactForm save(ContactForm contactForm) {
        ContactForm contactForm1 = contactFormRepository.save(contactForm);
        return contactForm1;
    }
}
