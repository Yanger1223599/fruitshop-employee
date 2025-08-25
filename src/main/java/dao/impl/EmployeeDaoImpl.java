package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Employee;
import util.DbConnection;

public class EmployeeDaoImpl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
private static Connection conn=DbConnection.getDb();
	
	public void add(Employee employee) {
		String sql="insert into employee(employeeno,name,username,password) "
				+ "values(?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);

			ps.setString(1, employee.getEmployeeNo());
			ps.setString(2, employee.getName());
			ps.setString(3, employee.getUsername());
			ps.setString(4, employee.getPassword());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Employee select(String username, String password) {
		Employee employee=null;
		String sql="select * from employee where username=? and password=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				employee=new Employee();
				employee.setId(rs.getInt("id"));
				employee.setEmployeeNo(rs.getString("employeeNo"));
				employee.setName(rs.getString("name"));
				employee.setUsername(rs.getString("username"));
				employee.setPassword(rs.getString("Password"));
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return employee;
	}

	public Employee select(String username) {
		Employee employee=null;
		String sql="select * from employee where username=?";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				employee=new Employee();
				employee.setId(rs.getInt("id"));
				employee.setEmployeeNo(rs.getString("employeeNo"));
				employee.setName(rs.getString("name"));
				employee.setUsername(rs.getString("username"));
				employee.setPassword(rs.getString("Password"));
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return employee;
	}

	public Employee select1(String name, String username) {
		Employee employee=null;
	
	String sql="select * from employee where name=? and username=?" ;
	try {
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, username);
		ResultSet rs=ps.executeQuery();
		
		if(rs.next())
		{
			employee=new Employee();
			employee.setId(rs.getInt("id"));
			employee.setEmployeeNo(rs.getString("employeeNo"));
			employee.setName(rs.getString("name"));
			employee.setUsername(rs.getString("username"));
			employee.setPassword(rs.getString("Password"));
			
			
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return employee;

	}
	
	
	public String generateEmployeeNo() {
        String sql = "SELECT employeeNo FROM employee ORDER BY id DESC LIMIT 1";
        try (	
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                String lastEmpNo = rs.getString("employeeNo"); // 例如 E007
                int num = Integer.parseInt(lastEmpNo.substring(1)); // 取出數字部分 007
                num++;
                return String.format("E%03d", num); // 轉成 E008
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "E001"; // 如果沒有資料，從 E001 開始
    }
	
	 public boolean register(Employee emp) {

		    String checkSql = "SELECT COUNT(*) FROM employee WHERE username = ?";
	        String sql = "INSERT INTO employee (employeeNo, name, username, password) VALUES (?, ?, ?, ?)";
	        try (
	             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
	             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	            
	        	 checkStmt.setString(1, emp.getUsername());
	             ResultSet rs = checkStmt.executeQuery();
	             if (rs.next() && rs.getInt(1) > 0) {
	                 return false; // 帳號已存在
	             }
	        	
	            // 自動生成 employeeNo
	            String newEmpNo = generateEmployeeNo();
	            emp.setEmployeeNo(newEmpNo);

	            ps.setString(1, emp.getEmployeeNo());
	            ps.setString(2, emp.getName());
	            ps.setString(3, emp.getUsername());
	            ps.setString(4, emp.getPassword());
	            return ps.executeUpdate() > 0;

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }


}


