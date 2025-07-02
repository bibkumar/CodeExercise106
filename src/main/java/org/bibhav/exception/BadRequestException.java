package org.bibhav.exception;

/**
 * Represents a custom exception to capture all request-related errors.
 * <p>
 * This exception is typically thrown when a client sends invalid or malformed data
 * in a request, violating the expected format or constraints. It serves as a mechanism
 * to provide meaningful feedback to the client about the nature of the error.
 * <p>
 * Typical use cases include validating input data, handling incorrect API calls,
 * and ensuring robust error handling in the application.
 *
 * @author BibhavKumar
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
