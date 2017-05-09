package com.andor.employee.bean;

import java.io.Serializable;

public class RestResponse<T extends Serializable> implements Serializable {
	
	private static final long serialVersionUID = 6207789905240524907L;

	private boolean success;
	private T entity;
	private String errorMessage;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

}
