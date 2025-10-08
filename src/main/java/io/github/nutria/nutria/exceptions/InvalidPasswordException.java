package io.github.nutria.nutria.exceptions;

public class InvalidPasswordException extends ValidationException {
    public InvalidPasswordException(String reason) {
        super("senha", "Senha inválida: " + reason);
    }

    public InvalidPasswordException() {
        super("senha", "A senha deve ter no mínimo 8 caracteres");
    }
}