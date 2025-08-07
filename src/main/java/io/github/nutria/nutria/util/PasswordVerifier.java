package io.github.nutria.nutria.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordVerifier {
    public static boolean checkPassword(String plaintextPassword, String storedHash) {
        return BCrypt.checkpw(plaintextPassword, storedHash);
    }
}