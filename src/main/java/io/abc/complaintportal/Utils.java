package io.abc.complaintportal;

import io.abc.complaintportal.resource.ComplaintResource;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <code>{@link Utils}</code> class to handle utils methods
 *
 * @author aya
 * @since v1.0
 */
public class Utils {

    /**
     * Method to generate hashed password
     *
     * @param password , <code>{@link String}</code>
     * @return <code>{@link String}</code> of the hashed password
     */
    public static String generateHashedPassword(String password) {

        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(")hjksr%qq5)=%8hibt34389py988gh%=jkakk".getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
