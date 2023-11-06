package edu.hw3.exceptions;

public class InvalidStringContentException extends Exception {
    public InvalidStringContentException() {
        super("Строка содержит недопустимые символы или она не сбалансирована");
    }
}
