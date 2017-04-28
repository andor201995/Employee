package com.andor.employee.exception;

public class DAOException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 305002994284912919L;

	public DAOException() {
		super("Connecting to DB Failed");
	}

}
