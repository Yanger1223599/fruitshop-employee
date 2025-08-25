package service;

import java.util.List;
import model.Employee;
import model.Porder;

public interface EmployeeService {
    
    // ----------------- 員工相關 -----------------
    // create
    boolean addEmployee(Employee employee); // 判斷帳號+新增
    
    // read
    Employee login(String username, String password); // 登入

    Employee findUsernameEmployee(String name, String username);

    // ----------------- 訂單相關 -----------------
    List<Porder> getAllOrders();

    public boolean updateOrder(Porder order);

    void deleteOrder(String orderNo);
}
