package io.github.nutria.nutria.exceptions;

public class DataAccessException extends NutriaException {
    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
