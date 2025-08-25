package model;

import java.io.Serializable;

public class Employee implements Serializable{
	
	private int Id ;
	private String employeeNo;
	private String name;
	private String username;
	private String password;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Employee(String employeeNo, String name, String username, String password) {
		super();
		
		this.employeeNo = employeeNo;
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
