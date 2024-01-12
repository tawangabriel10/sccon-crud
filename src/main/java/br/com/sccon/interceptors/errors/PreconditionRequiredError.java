package br.com.sccon.interceptors.errors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PreconditionRequiredError extends MainError {

	public PreconditionRequiredError(Integer status, String message) {
		super();
		this.setStatus(status);
		this.setMessage(message);
	}

}
