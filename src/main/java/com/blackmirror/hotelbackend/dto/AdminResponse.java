package com.blackmirror.hotelbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResponse {
    public Integer code = 404;
    public String message = "This user not found.";

}
