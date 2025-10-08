package io.github.nutria.nutria.exceptions;

public class ConfigurationException extends NutriaException {
    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}