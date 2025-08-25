package dao;

import java.util.List;

import model.Product;

public interface ProductDao {
	
    Product findByNo(String productNo);
    List<Product> getAll();
	void addProduct(Product productNo);
	void delete(String productNo);
	void save(Product product);
	
}
