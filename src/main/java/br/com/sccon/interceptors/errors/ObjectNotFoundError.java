package br.com.sccon.interceptors.errors;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ObjectNotFoundError extends MainError {
	public ObjectNotFoundError() {}
	
	public ObjectNotFoundError(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	@Override
	public String toString() {
		return "{\n    status : " + status + ",\n    message : " + message + "\n}";
	}
}
