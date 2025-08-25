package model;

import java.io.Serializable;

public class Product implements Serializable{
    private int id;
    private String productno;
    private String name;
    private int price;
    
    
    


	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product( String productno, String name, int price) {
		super();
		this.productno = productno;
		this.name = name;
		this.price = price;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductno() {
		return productno;
	}

	public void setProductno(String productno) {
		this.productno = productno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	

	
}