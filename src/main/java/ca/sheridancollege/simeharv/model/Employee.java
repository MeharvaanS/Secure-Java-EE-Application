package ca.sheridancollege.simeharv.model;

import java.io.Serializable;

public class Employee implements Serializable {
// properties of Course object
	private int employeeNumber;// to be used as primary key
	private String employeeName;
	private long employeePhone;
	private String employeeEmail;
	// no-argument constructor
	public Employee() {
	}
	// setters and getters
	
	public int getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public long getEmployeePhone() {
		return employeePhone;
	}
	public void setEmployeePhone(long employeePhone) {
		this.employeePhone = employeePhone;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	

}