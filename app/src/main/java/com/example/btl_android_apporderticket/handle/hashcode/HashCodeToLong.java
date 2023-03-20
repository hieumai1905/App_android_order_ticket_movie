package com.example.btl_android_apporderticket.handle.hashcode;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCodeToLong {

    public static long getHashedId(String id) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = id.getBytes();
            byte[] hashedBytes = md.digest(bytes);
            BigInteger bigInt = new BigInteger(1, hashedBytes);
            return bigInt.longValue();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
