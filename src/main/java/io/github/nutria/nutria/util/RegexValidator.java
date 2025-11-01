package io.github.nutria.nutria.util;

public class RegexValidator {
    public static boolean ehEmailValido(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    public static boolean ehTelefoneValido(String telefone) {
        return telefone != null && telefone.matches("^\\(?[0-9]{2}\\)? ?[0-9]{5}-?[0-9]{4}$");
    }

    public static boolean ehSenhaValida(String senha) {
        return senha != null && senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+={}\\[\\].?-]).{8,}$");
    }
}
