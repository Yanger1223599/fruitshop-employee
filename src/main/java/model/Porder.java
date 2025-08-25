package model;

import java.io.Serializable;

public class Porder implements Serializable{
    private int id;
    private String orderNo;
    private String customerId;
    private String productno;
    private int amount;
    
	public Porder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Porder(String orderNo, String customerId, String productno, int amount) {
		super();
		this.orderNo = orderNo;
		this.customerId = customerId;
		this.productno = productno;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getProductno() {
		return productno;
	}

	public void setProductno(String productno) {
		this.productno = productno;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}