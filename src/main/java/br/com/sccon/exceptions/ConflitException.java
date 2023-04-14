package br.com.sccon.exceptions;

public class ConflitException extends RuntimeException {

    public ConflitException() {
        super();
    }

    public ConflitException(String message) {
        super(message);
    }
}
