package edu.hw3.exceptions;

public class WrongInputException extends Exception {
    public WrongInputException() {
        super("Неправильный ввод для функции");
    }
}
