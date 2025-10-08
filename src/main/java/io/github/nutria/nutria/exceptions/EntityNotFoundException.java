package io.github.nutria.nutria.exceptions;

public class EntityNotFoundException extends DataAccessException {
    private final String entityName;
    private final Object identifier;

    public EntityNotFoundException(String entityName, Object identifier) {
        super(String.format("%s não encontrado(a) com identificador: %s", entityName, identifier));
        this.entityName = entityName;
        this.identifier = identifier;
    }

    public String getEntityName() {
        return entityName;
    }

    public Object getIdentifier() {
        return identifier;
    }
}
