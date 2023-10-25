package edu.project1.exceptions;

public class EmptyFileException extends Exception {
    private final String filepath;

    public EmptyFileException(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public String getMessage() {
        return "File " + filepath + " is empty";
    }
}
