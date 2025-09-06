package io.github.nutria.nutria.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordVerifier {

    /** Método estático responsável por verificar se a senha original condiz
     * com o hash correto.
     * @return boolean - Retorna true caso a senha seja igual o hash armazenado
     * @param plaintextPassword - Recebe a senha da requisição que será feita o hash
     * @param storedHash - Recebe o hash armazenado
     * */
    public static boolean checkPassword(String plaintextPassword, String storedHash) {
        return BCrypt.checkpw(plaintextPassword, storedHash);
    }
}