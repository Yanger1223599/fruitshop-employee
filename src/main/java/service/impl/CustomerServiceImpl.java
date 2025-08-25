package service.impl;

import dao.impl.CustomerDaoImpl;
import model.Customer;
import service.CustomerService;

public class CustomerServiceImpl implements CustomerService{

	public static void main(String[] args) {
		
		System.out.println(new CustomerServiceImpl().login("def", "1234"));

	}

	private static CustomerDaoImpl cdi=new CustomerDaoImpl();
	
	

	@Override
	public Customer login(String username, String password) {
		
		
		
		return cdi.select(username, password);
	}

	



	public Customer findUsernameCustomer(String name, String username) {
		// TODO Auto-generated method stub
		return cdi.select1(name,username);
	}

	@Override
	public boolean addCustomer(Customer customer) {
		boolean isUsernameBeenUse=false;
		Customer  c = cdi.select(customer.getUsername()); 
		if(c==null)
		{
			cdi.addCustomer(customer);
			isUsernameBeenUse = true;
		}
		
		return isUsernameBeenUse;
	}





	@Override
	public Customer select(String username, String password) {
		// TODO Auto-generated method stub
		return cdi.select(username, password);
	}





	@Override
	public Customer select(String username) {
		// TODO Auto-generated method stub
		return cdi.select(username);
	}





	@Override
	public Customer select1(String name, String username) {
		// TODO Auto-generated method stub
		return cdi.select(name,username);
	}

}