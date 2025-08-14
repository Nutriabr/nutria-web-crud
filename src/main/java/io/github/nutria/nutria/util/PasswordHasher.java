package io.github.nutria.nutria.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {
    private final static int workload = 12;

    public static String hashPassword(String plaintextPassword) {
        return BCrypt.hashpw(plaintextPassword, BCrypt.gensalt(workload));
    }

}
