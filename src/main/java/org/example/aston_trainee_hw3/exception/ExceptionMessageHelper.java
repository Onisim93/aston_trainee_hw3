package org.example.aston_trainee_hw3.exception;

/**
 * Helper class for generating exception messages with additional context information.
 */
public class ExceptionMessageHelper {

    private ExceptionMessageHelper() {}

    public static String entityNotFoundMsg(Long id) {
        return String.format("Entity with id: %d not found", id);
    }

}
