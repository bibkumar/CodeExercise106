package org.bibhav.exception;

/**
 * To capture all Application level exceptions.
 *
 * @author BibhavKumar
 */
public class ApplicationException extends Exception {
    public ApplicationException(String message) {
        super(message);
    }
}
