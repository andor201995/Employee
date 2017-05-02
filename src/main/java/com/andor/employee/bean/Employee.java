package com.andor.employee.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Employee {
	private Integer _id;
	private String firstName;
	private String lastName;
	private String designation;
	private String gender;

	public Employee() {
	}

	public Employee(String _id, String firstName, String lastName,
			String designation, String gender) {
		if (_id != null) {
			this._id = Integer.parseInt(_id);
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.designation = designation;
		this.gender = gender;
	}

	@JsonIgnore
	public Integer get_Id() {
		return _id;
	}

	public void set_Id(Integer _Id) {
		this._id = _Id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
