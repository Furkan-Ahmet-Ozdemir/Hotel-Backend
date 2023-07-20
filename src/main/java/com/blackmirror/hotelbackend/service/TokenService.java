package com.blackmirror.hotelbackend.service;

import com.blackmirror.hotelbackend.entity.Token;
import com.blackmirror.hotelbackend.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenService{

    @Autowired
    private TokenRepository tokenRepository;


}
