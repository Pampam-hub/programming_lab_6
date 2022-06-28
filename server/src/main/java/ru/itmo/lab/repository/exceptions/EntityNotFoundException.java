package ru.itmo.lab.repository.exceptions;

public class EntityNotFoundException extends Exception {
    private static final String messageFormat = "Entity %s with id %d not founded!";

    public EntityNotFoundException(Class<?> clazz, Integer id) {
        super(String.format(messageFormat, clazz.getSimpleName(), id));
    }
}
