package io.github.nutria.nutria.exceptions;

public class NutriaException extends RuntimeException {
    public NutriaException(String message) {
        super(message);
    }

    public NutriaException(String message, Throwable cause) {
        super(message, cause);
    }
}
