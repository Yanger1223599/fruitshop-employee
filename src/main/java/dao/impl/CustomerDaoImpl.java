package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CustomerDao;
import model.Customer;
import util.DbConnection;

public class CustomerDaoImpl implements CustomerDao {

	
	Connection conn = DbConnection.getDb();
    @Override
    public Customer login(String username, String password) {
        Customer c = null;
        String sql = "SELECT * FROM customer WHERE username=? AND password=?";
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Customer();
                c.setId(rs.getInt("id"));
                c.setCustomerid(rs.getString("customerid"));
                c.setName(rs.getString("name"));
                c.setUsername(rs.getString("username"));
                c.setPassword(rs.getString("password"));
                c.setAddress(rs.getString("address"));
                c.setPhone(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

   

    @Override
    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try (
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setCustomerid(rs.getString("customerid"));
                c.setName(rs.getString("name"));
                c.setUsername(rs.getString("username"));
                c.setPassword(rs.getString("password"));
                c.setAddress(rs.getString("address"));
                c.setPhone(rs.getString("phone"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

	@Override
	public void addCustomer(Customer c) {
		String CustomerId = generateCustomerId();
	    String sql = "INSERT INTO customer(customerid,name,username,password,address,phone) VALUES(?,?,?,?,?,?)";
	    try (
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, CustomerId);  // 自動生成的 ID
	        ps.setString(2, c.getName());
	        ps.setString(3, c.getUsername());
	        ps.setString(4, c.getPassword());
	        ps.setString(5, c.getAddress());
	        ps.setString(6, c.getPhone());
	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}
	public Customer select(String username, String password) {
		Customer customer=null;
		String sql="select * from customer where username=? and password=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				customer=new Customer();
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("customerid"));
				customer.setName(rs.getString("name"));
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("Password"));
				customer.setAddress(rs.getString("address"));
				customer.setPhone(rs.getString("phone"));
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return customer;
	}
	
	
	
	
	
	 public String ensureCustomerId(String username) {
	        String customerId = null;
	        String sqlSelect = "SELECT customerid FROM customer WHERE username = ?";
	        String sqlUpdate = "UPDATE customer SET customerid = ? WHERE username = ?";

	        try (PreparedStatement ps = conn.prepareStatement(sqlSelect)) {
	            ps.setString(1, username);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                customerId = rs.getString("customerid");
	            }

	            if (customerId == null || customerId.isEmpty()) {
	                customerId = generateCustomerId();
	                try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
	                    psUpdate.setString(1, customerId);
	                    psUpdate.setString(2, username);
	                    psUpdate.executeUpdate();
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return customerId;
	    }
	 
	 
	 
	private String generateCustomerId() {
	    String prefix = "C";
	    String sql = "SELECT customerid FROM customer ORDER BY id DESC LIMIT 1";
	    try (
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {
	        if (rs.next()) {
	            String lastId = rs.getString("customerid"); // 例如 C005
	            int num = Integer.parseInt(lastId.substring(1)); // 取 005 -> 5
	            num++;
	            return prefix + String.format("%03d", num); // C006
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "C001"; // 如果資料庫是空的，從 C000 開始
	}

	
	@Override
	public Customer select(String username) {
		Customer customer=null;
		String sql="select * from customer where username=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				customer=new Customer();
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("customerid"));
				customer.setName(rs.getString("name"));
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("Password"));
				customer.setAddress(rs.getString("address"));
				customer.setPhone(rs.getString("phone"));
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return customer;
	}

	@Override
	public Customer select1(String name, String username) {
		Customer customer=null;
	
	String sql="select * from customer where name=? and username=?" ;
	try {
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, username);
		ResultSet rs=ps.executeQuery();
		
		if(rs.next())
		{
			customer=new Customer();
			customer.setId(rs.getInt("id"));
			customer.setName(rs.getString("customerid"));
			customer.setName(rs.getString("name"));
			customer.setUsername(rs.getString("username"));
			customer.setPassword(rs.getString("Password"));
			customer.setAddress(rs.getString("address"));
			customer.setPhone(rs.getString("phone"));
			
			
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return customer;

	}

}
