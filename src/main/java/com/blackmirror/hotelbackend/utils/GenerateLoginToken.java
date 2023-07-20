package com.blackmirror.hotelbackend.utils;

import java.util.UUID;

public class GenerateLoginToken {
    public static String generateUniqueLoginToken(){

        return UUID.randomUUID().toString();
    }
}
