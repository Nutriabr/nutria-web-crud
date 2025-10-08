package io.github.nutria.nutria.exceptions;

public class DuplicateEmailException extends RulesException {
    private final String email;

    public DuplicateEmailException(String email) {
        super(String.format("Email '%s' já está cadastrado no sistema", email));
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
