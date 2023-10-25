package edu.project1.exceptions;

public class ShortWordException extends Exception {
    @Override
    public String getMessage() {
        return "The generated word is too short";
    }
}
