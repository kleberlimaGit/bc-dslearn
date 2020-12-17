package com.bc.devsuperior.dslearn.services.exception;

public class ForbiddenException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ForbiddenException(String msg) {
		super(msg);
	}
}
