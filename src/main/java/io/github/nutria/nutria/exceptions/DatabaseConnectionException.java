package io.github.nutria.nutria.exceptions;

public class DatabaseConnectionException extends NutriaException {
  public DatabaseConnectionException(String message, Throwable cause) {
    super("Erro ao conectar com o banco de dados: " + message, cause);
  }
}
