package br.com.sccon.interceptors;

import br.com.sccon.exceptions.ConflitException;
import br.com.sccon.exceptions.ObjectNotFoundException;
import br.com.sccon.exceptions.PreconditionErrorException;
import br.com.sccon.interceptors.errors.ConflitError;
import br.com.sccon.interceptors.errors.ObjectNotFoundError;
import br.com.sccon.interceptors.errors.PreconditionRequiredError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class InterceptorsHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ObjectNotFoundError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		ObjectNotFoundError error = new ObjectNotFoundError(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(PreconditionErrorException.class)
	public ResponseEntity<PreconditionRequiredError> preconditionError(PreconditionErrorException e, HttpServletRequest request) {
		PreconditionRequiredError error = new PreconditionRequiredError(HttpStatus.PRECONDITION_REQUIRED.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED).body(error);
	}

	@ExceptionHandler(ConflitException.class)
	public ResponseEntity<ConflitError> conflit(ConflitException e, HttpServletRequest request) {
		ConflitError error = new ConflitError(HttpStatus.CONFLICT.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
}
