package org.bibhav.exception;

/**
 * Represents a custom exception to capture all application-level errors.
 * <p>
 * This exception serves as a base class for exceptions that occur within the application,
 * providing a mechanism to encapsulate error details and propagate meaningful messages
 * throughout the system. It is designed to be extended for specific exception types
 * to ensure consistent error handling and reporting.
 * <p>
 * Typical use cases include handling unexpected application states, propagating
 * errors from service layers, and ensuring robust exception management.
 *
 * @author BibhavKumar
 */
public class ApplicationException extends RuntimeException {
    public ApplicationException(String message) {
        super(message);
    }
}
