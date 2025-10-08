package io.github.nutria.nutria.exceptions;

public class InvalidCredentialsException extends AuthenticationException {
    public InvalidCredentialsException() {
        super("Email ou senha incorretos");
    }
}
