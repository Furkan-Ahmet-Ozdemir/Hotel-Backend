package com.blackmirror.hotelbackend.controller;

import com.blackmirror.hotelbackend.entity.ContactForm;
import com.blackmirror.hotelbackend.entity.InvoiceGuest;
import com.blackmirror.hotelbackend.service.ContactFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactFormController {
    @Autowired
    private ContactFormService contactFormService;
    @PostMapping("/contactForm/save")
    public ContactForm saveContactForm(@RequestBody ContactForm contactForm){
        ContactForm contactForm1 = contactFormService.save(contactForm);
        System.out.println("----------"+contactForm1+"----------------");
        return contactForm1;
    }
}
