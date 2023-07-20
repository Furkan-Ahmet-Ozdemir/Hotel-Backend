package com.blackmirror.hotelbackend.service;

import com.blackmirror.hotelbackend.entity.Admin;
import com.blackmirror.hotelbackend.entity.Token;
import com.blackmirror.hotelbackend.repository.AdminRepository;
import com.blackmirror.hotelbackend.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class AdminService {

    @Autowired
    private AdminRepository adminRepository;


    @Autowired
    private TokenRepository tokenRepository;

    public Admin getAdmin(String username, String password){
        Optional<Admin> admin= adminRepository.findAdminByUserNameAndPassword(username, password);
        if(admin.isPresent())
            return admin.get();
        else
        return null;
    }

    public Admin addTokenAdmin(Admin admin, String tokenString)
    {
        Token token= new Token();
        token.setTokenString(tokenString);
        token = tokenRepository.save(token);
        List<Token> tokenList = admin.getTokens();
        tokenList.add(token);
        admin.setTokens(tokenList);
        return adminRepository.save(admin);
    }

    private List<Admin> listAll(){
        return adminRepository.findAll();
    }

    public Admin getMatchingAdmin(String tokenString){
        Admin adminResult = new Admin();
        List<Admin> admins= this.listAll();

        for (Admin admin: admins){
            for (Token token:admin.getTokens())
            {
                if(token.getTokenString().equals(tokenString))
                    adminResult=admin;

            }


        }
        return adminResult;
    }
    public void deleteTokens(Admin admin){
        List<Token> tokenList =admin.getTokens();

        tokenRepository.deleteAll(tokenList);

    }

}
