package br.com.sccon.exceptions;

public class PreconditionErrorException extends RuntimeException {

    public PreconditionErrorException() {
        super();
    }

    public PreconditionErrorException(String message) {
        super(message);
    }
}
