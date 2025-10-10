package io.github.nutria.nutria.exceptions;

public class InvalidPhoneException extends ValidationException {
    public InvalidPhoneException(String telefone) {
        super("telefone", String.format("Telefone inválido: %s", telefone));
    }
}