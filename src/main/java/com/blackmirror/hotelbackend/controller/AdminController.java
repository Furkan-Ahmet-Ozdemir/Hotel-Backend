package com.blackmirror.hotelbackend.controller;

import com.blackmirror.hotelbackend.dto.AdminResponse;
import com.blackmirror.hotelbackend.entity.Admin;
import com.blackmirror.hotelbackend.service.AdminService;
import com.blackmirror.hotelbackend.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.blackmirror.hotelbackend.utils.GenerateLoginToken.generateUniqueLoginToken;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/admin")
    public AdminResponse saveContactForm(@RequestBody Admin adminRequest){
        AdminResponse adminResponse = new AdminResponse();
        String token = generateUniqueLoginToken();
        Admin admin = adminService.getAdmin(adminRequest.getUserName(),adminRequest.getPassword());
        if(admin!=null){
            adminResponse.setCode(200);
            adminResponse.setMessage(token);
            adminService.addTokenAdmin(admin,token);
            return adminResponse;
        }
            return adminResponse;
    }

    @GetMapping(value = "/logout/{id}")
    public AdminResponse getById(@PathVariable String id) {
        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setCode(200);
        adminResponse.setMessage("Logout Successful.");
        Admin admin = adminService.getMatchingAdmin(id);
        adminService.deleteTokens(admin);
        return adminResponse;
    }

}
