package com.TinkerersLab.LabAssistant.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.stereotype.Component;

import com.TinkerersLab.LabAssistant.config.ApplicationConstants;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class Utils {

    public String getFileHash(File file) {
        byte[] buffer = new byte[8192];
        int count;
        MessageDigest messageDigest;

        try {
            messageDigest = MessageDigest.getInstance(ApplicationConstants.DEFAULT_HASH_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            messageDigest = null;
        }

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            while ((count = bis.read(buffer)) > 0) {
                messageDigest.update(buffer, 0, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] hash = messageDigest.digest();

        return Base64.getEncoder().encodeToString(hash);
    }
}
