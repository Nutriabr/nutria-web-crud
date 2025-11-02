package io.github.nutria.nutria.util;

public class RegexValidator {
    /**
     * Verifica se o email fornecido corresponde a um formato de email válido.
     * Ex: {@code usuario@dominio.com}
     *
     * @param email A string de email a ser validada.
     * @return {@code true} se o email for válido e não nulo; {@code false} caso contrário.
     */
    public static boolean ehEmailValido(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    /**
     * Verifica se o telefone fornecido corresponde a um formato de telefone válido.
     * Aceita formatos com ou sem parênteses no DDD, com ou sem espaço após o DDD,
     * e com ou sem hífen separando os últimos quatro dígitos.
     * Ex: {@code (11) 98765-4321} ou {@code 11987654321}.
     *
     * @param telefone A string de telefone a ser validada.
     * @return {@code true} se o telefone for válido e não nulo; {@code false} caso contrário.
     */
    public static boolean ehTelefoneValido(String telefone) {
        return telefone != null && telefone.matches("^\\(?[0-9]{2}\\)? ?[0-9]{5}-?[0-9]{4}$");
    }

    /**
     * Verifica se a senha fornecida atende aos requisitos do regex.
     * <p>
     * A senha deve conter:
     * <ul>
     * <li>Pelo menos 8 caracteres.</li>
     * <li>Pelo menos uma letra minúscula.</li>
     * <li>Pelo menos uma letra maiúscula.</li>
     * <li>Pelo menos um dígito numérico.</li>
     * <li>Pelo menos um caractere especial (ex: {@code !@#$%^&*()_+...}).</li>
     * </ul>
     *
     * @param senha A string de senha a ser validada.
     * @return {@code true} se a senha for válida e não nula; {@code false} caso contrário.
     */
    public static boolean ehSenhaValida(String senha) {
        return senha != null && senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+={}\\[\\].?-]).{8,}$");
    }
}
