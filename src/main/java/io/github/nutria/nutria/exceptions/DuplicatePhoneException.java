package io.github.nutria.nutria.exceptions;

public class DuplicatePhoneException extends RulesException {
    private final String phone;

    public DuplicatePhoneException(String phone) {
        super(String.format("Telefone %s já está cadastrado no sistema", phone));
        this.phone = phone;
    }

    public String getEmail() {
        return phone;
    }
}
