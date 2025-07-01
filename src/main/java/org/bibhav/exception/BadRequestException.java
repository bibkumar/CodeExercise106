package org.bibhav.exception;

/**
 * To capture all request related exceptions.
 *
 * @author BibhavKumar
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
