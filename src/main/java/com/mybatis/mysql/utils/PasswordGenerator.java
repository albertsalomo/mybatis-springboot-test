package com.mybatis.mysql.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

import static ch.qos.logback.core.encoder.ByteArrayUtil.hexStringToByteArray;

public class PasswordGenerator {
    public String PBKDF2HashPassword(String password) throws
            NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = hexStringToByteArray("e04fd020ea3a6910a2d808002b30309d");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = f.generateSecret(spec).getEncoded();
        Base64.Encoder enc = Base64.getEncoder();
        return enc.encodeToString(hash);
    }
}
