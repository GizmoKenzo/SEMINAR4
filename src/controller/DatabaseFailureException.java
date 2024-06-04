package controller;

/**
 * Thrown when there is a database failure.
 */
public class DatabaseFailureException extends Exception {

    public DatabaseFailureException(String message) {
        super(message);
    }
}
