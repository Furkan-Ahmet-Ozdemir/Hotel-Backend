package com.blackmirror.hotelbackend.utils;

import java.time.Instant;
import java.util.Random;

public class GeneratePNR {
    private static final String PREFIX = "PNR";
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int RANDOM_LENGTH = 3;

    public static String generateUniquePNR() {
        String timestamp = String.valueOf(Instant.now().toEpochMilli());
        String lastSixChars = timestamp.substring(timestamp.length() - 6);
        StringBuilder sb = new StringBuilder(PREFIX + lastSixChars);

        Random random = new Random();
        for (int i = 0; i < RANDOM_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
