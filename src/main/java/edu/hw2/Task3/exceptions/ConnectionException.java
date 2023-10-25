package edu.hw2.Task3.exceptions;

public class ConnectionException extends RuntimeException {
    public ConnectionException() {
    }

    public ConnectionException(Throwable exception) {
        super(exception);
    }
}
