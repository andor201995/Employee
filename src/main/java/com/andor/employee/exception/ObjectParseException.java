package com.andor.employee.exception;

public class ObjectParseException extends Exception{
	private static final long serialVersionUID = 305002994284912919L;

	public ObjectParseException() {
		super("Error while parsing object or Json");
	}

}
