package com.payoda.employee.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Employee {
	private Integer _id;
	private String firstName;
	private String lastName;
	private String designation;
	private String gender;
	private Date dateOB;
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
	public Date getDateOB() {
		return dateOB;
	}
	public void setDateOB(Date dateOB) {
		this.dateOB = dateOB;
	}


}
