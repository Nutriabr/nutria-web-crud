package io.github.nutria.nutria.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {

    private final static int workload = 12;

    /** Método estático responsável por gerar o hash da semanha, usando um workload 12
     * @return String - Retorna a senha com o hash armazenado
     * @param plaintextPassword - Recebe a senha da requisição que será feita o hash
     * */
    public static String hashPassword(String plaintextPassword) {
        return BCrypt.hashpw(plaintextPassword, BCrypt.gensalt(workload));
    }
}
