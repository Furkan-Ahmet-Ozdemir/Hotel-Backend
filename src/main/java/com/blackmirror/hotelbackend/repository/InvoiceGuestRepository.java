package com.blackmirror.hotelbackend.repository;

import com.blackmirror.hotelbackend.entity.InvoiceGuest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceGuestRepository extends JpaRepository<InvoiceGuest,Long> {
    public Long countById(Long id);
}
