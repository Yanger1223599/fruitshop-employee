package dao;

import java.util.List;

import model.Porder;

public interface PorderDao {
	
    void addOrder(Porder order);
    public int updateOrder(Porder order);
    void deleteOrder(String orderNo);
    List<Porder> getAllOrders();
    List<Porder> getOrdersByCustomer(String customerId);
	static int getTotalSalesByProduct(String productno) {
		// TODO Auto-generated method stub
		return 0;
	}
	List<Porder> getOrdersByProductNo(String productno);


}
