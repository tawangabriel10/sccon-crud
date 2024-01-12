package br.com.sccon.interceptors.errors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConflitError extends MainError {
    public ConflitError() {}

    public ConflitError(Integer status, String message) {
        super();
        this.status = status;
        this.message = message;
    }
}
