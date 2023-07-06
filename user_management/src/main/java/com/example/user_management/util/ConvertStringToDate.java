package com.example.user_management.util;

import java.security.SecureRandom;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;

public class ConvertStringToDate {

    public static Date convert(String dateString, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            java.util.Date utilDate = dateFormat.parse(dateString);
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // Tạo một chuỗi secret key ngẫu nhiên với kích thước 64 bytes
        byte[] secretKeyBytes = new byte[64];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(secretKeyBytes);

        // Mã hóa chuỗi secret key thành dạng Base64
        String secretKey = Base64.getEncoder().encodeToString(secretKeyBytes);

        // In chuỗi secret key
        System.out.println("Secret Key: " + secretKey);
    }

}
