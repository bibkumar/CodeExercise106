package org.bibhav.exception;

/**
 * To capture all request related exceptions.
 *
 * @author BibhavKumar
 */
public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        super(message);
    }
}
