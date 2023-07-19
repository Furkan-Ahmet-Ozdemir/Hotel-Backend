package com.blackmirror.hotelbackend.repository;

import com.blackmirror.hotelbackend.entity.ContactForm;
import com.blackmirror.hotelbackend.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactFormRepository extends JpaRepository<ContactForm,Long> {
}
