package edu.project1.exceptions;

public class OpeningFileException extends Exception {
    private final String filepath;

    public OpeningFileException(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public String getMessage() {
        return "File " + filepath + " cannot be opened";
    }
}
