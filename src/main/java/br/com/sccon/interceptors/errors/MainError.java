package br.com.sccon.interceptors.errors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class MainError implements Serializable {
	private static final long serialVersionUID = 1L;
	protected Integer status;
	protected String message;
	protected String tag;

	public MainError(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public MainError(Integer status, String message, String tag) {
		super();
		this.status = status;
		this.message = message;
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "{\n    status : " + status + ",\n    message : " + message + "\n, \n    tag : " + tag + "\n}";
	}
}
