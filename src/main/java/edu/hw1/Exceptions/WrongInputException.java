package edu.hw1.Exceptions;

public class WrongInputException extends Exception {
    public WrongInputException() {
        super("Неправильный ввод для функции");
    }
}
