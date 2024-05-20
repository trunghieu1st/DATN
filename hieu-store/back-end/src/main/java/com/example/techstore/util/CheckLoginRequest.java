package com.example.techstore.util;

import java.util.regex.Pattern;

public class CheckLoginRequest {

    private static final Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern phonePattern = Pattern.compile("^(0?)(3[2-9]|5[689]|7[0|6-9]|8[1-9]|9[0-9])[0-9]{7}$");

    public static Boolean isEmail(String email) {
        return emailPattern.matcher(email).matches();
    }

    public static Boolean isPhone(String phone) {
        return phonePattern.matcher(phone).matches();
    }

}
