package com.application.parking.Gere_Parking.G_Menu.Abonement;

import java.util.Random;

public class CreationPadge {

    public static String generateRandomString() {
        final int length = 14 ;
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateRandomString());
    }
}
