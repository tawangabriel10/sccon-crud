package br.com.sccon.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException() {
        super();
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
