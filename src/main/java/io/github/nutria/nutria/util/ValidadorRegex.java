package io.github.nutria.nutria.util;

public class ValidadorRegex {
    public static boolean ehEmailValido(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    public static boolean ehTelefoneValido(String telefone) {
        return telefone != null && telefone.matches("^\\(?[0-9]{2}\\)? ?[0-9]{5}-?[0-9]{4}$");
    }
}
