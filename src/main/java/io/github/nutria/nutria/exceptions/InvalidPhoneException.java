package io.github.nutria.nutria.exceptions;

public class InvalidPhoneException extends ValidationException {
    public InvalidPhoneException(String telefone) {
        super("telefone", String.format("Formato de telefone inválido: %s", telefone));
    }
}