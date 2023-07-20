package com.blackmirror.hotelbackend.repository;

import com.blackmirror.hotelbackend.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface AdminRepository extends JpaRepository<Admin,Long> {

    public Optional<Admin> findAdminByUserNameAndPassword(String userName, String password);
    public List<Admin> findAll();


}
