package edu.hw2.Exceptions;

public class ConnectionException extends RuntimeException {
    public ConnectionException() {
    }

    public ConnectionException(Throwable exception) {
        super(exception);
    }
}
