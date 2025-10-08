package io.github.nutria.nutria.exceptions;

public class InvalidNumberException extends ValidationException {
  public InvalidNumberException(String field, String reason) {
    super(field, String.format("Valor numérico inválido no campo '%s': %s", field, reason));
  }
}
