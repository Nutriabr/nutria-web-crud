package io.github.nutria.nutria.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {

    private final static int workload = 12;

    /** Método estático responsável por gerar o hash da semanha, usando um workload 12
     * @return String - Retorna a senha com o hash armazenado
     * @param senhaOriginal - Recebe a senha da requisição que será feita o hash
     * */
    public static String hashSenha(String senhaOriginal) {
        return BCrypt.hashpw(senhaOriginal, BCrypt.gensalt(workload));
    }

    /** Método estático responsável por verificar se a senha original condiz com o hash correto.
     * @return boolean - Retorna true caso a senha seja igual o hash armazenado
     * @param senhaRecebida - Recebe a senha da requisição que será feita o hash
     * @param senhaArmezanada - Recebe o hash armazenado
     * */
    public static boolean verificarSenha(String senhaRecebida, String senhaArmezanada) {
        return BCrypt.checkpw(senhaRecebida, senhaArmezanada);
    }
}
