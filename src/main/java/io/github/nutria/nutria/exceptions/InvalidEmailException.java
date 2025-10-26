package io.github.nutria.nutria.exceptions;

public class InvalidEmailException extends ValidationException {
    public InvalidEmailException(String email) {
        super("email", String.format("Formato de mail inválido: %s", email));
    }
}