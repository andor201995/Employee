package com.andor.employee.exception;

public class WebResourceNotFoundException extends Exception{
	public WebResourceNotFoundException() {
		super("<html> " + "<title>" + "Hello Jersey" + "</title>"
				+ "<body><h1>"
				+ "Web Resource not found"
				+ "</body></h1>" + "</html> ");
	}

}
