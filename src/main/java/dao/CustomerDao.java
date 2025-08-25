package dao;

import java.util.List;

import model.Customer;

public interface CustomerDao {
	//C
    Customer login(String username, String password);
    //R
    void addCustomer(Customer c);
    List<Customer> getAll();
	Customer select(String username,String password);

	Customer select(String username );
	Customer select1(String name ,String username);
    //U
    //D
}
