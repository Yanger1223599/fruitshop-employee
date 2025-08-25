package dao;

import model.Employee;

public interface EmployeeDao {
	
	//create
	void add(Employee employee);
	
	
	//read
	Employee select(String username,String password);

	Employee select(String username );
	Employee select1(String name ,String username);
	String generateEmployeeNo();



	//update
	
	
	//delete
}
