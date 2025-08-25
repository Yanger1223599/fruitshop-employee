package service;

import model.Customer;

public interface CustomerService {
	//create
	boolean addCustomer(Customer customer);//判斷帳號+新增
	
	
	//read
	Customer login(String username,String password);//登入
	Customer select(String username,String password);

	Customer select(String username );
	Customer select1(String name ,String username);
	//update
	
	//delete

}