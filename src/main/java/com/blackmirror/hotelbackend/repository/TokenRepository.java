package com.blackmirror.hotelbackend.repository;

import com.blackmirror.hotelbackend.entity.Admin;
import com.blackmirror.hotelbackend.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token,Long> {

}
