package service.impl;

import java.util.List;

import dao.PorderDao;
import dao.impl.PorderDaoImpl;
import dao.impl.EmployeeDaoImpl;
import model.Employee;
import model.Porder;
import service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

    private static EmployeeDaoImpl edi = new EmployeeDaoImpl();
    private static PorderDao orderDao = new PorderDaoImpl();

    // ----------------- 員工相關 -----------------
    @Override
    public boolean addEmployee(Employee employee) {
      
    	
    	
        Employee e = edi.select(employee.getUsername());
        if (e == null) {
            edi.add(employee);
            return true; 
        }
        return false; 
    }

    @Override
    public Employee login(String username, String password) {
        return edi.select(username, password);
    }

    @Override
    public Employee findUsernameEmployee(String name, String username) {
        return edi.select1(name, username);
    }

    // ----------------- 訂單相關 -----------------
    @Override
    public List<Porder> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public boolean updateOrder(Porder order) {
        int rows = orderDao.updateOrder(order);
        return rows > 0;
    }



	@Override
	public void deleteOrder(String orderNo) {

        orderDao.deleteOrder(orderNo);
		
	}




    
}
