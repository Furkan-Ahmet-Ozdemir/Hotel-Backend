package com.blackmirror.hotelbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReservationSearchRequest {
    private Date checkInDate;
    private Date checkOutDate;
    private int customerCount;
}
