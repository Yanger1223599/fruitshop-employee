package dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.PorderDao;
import model.Porder;
import util.DbConnection;

public class PorderDaoImpl implements PorderDao {
	
	Connection conn = DbConnection.getDb();
    @Override
    public void addOrder(Porder order) {
    	    String newOrderNo = generateOrderNo();
    	    String sql = "INSERT INTO porder(orderNo, customerId, productno, amount) VALUES (?, ?, ?, ?)";
    	    try (
    	         PreparedStatement ps = conn.prepareStatement(sql)) {
    	        ps.setString(1, newOrderNo);   // 自動生成的訂單編號
    	        ps.setString(2, order.getCustomerId());
    	        ps.setString(3, order.getProductno());
    	        ps.setInt(4, order.getAmount());
    	        ps.executeUpdate();
    	       
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	}

    	/**
    	 * 生成新的 orderNo，格式 O0001 ~ O9999
    	 */
    	private String generateOrderNo() {
    	    String prefix = "O";
    	    String sql = "SELECT orderNo FROM porder ORDER BY id DESC LIMIT 1";
    	    try (
    	         PreparedStatement ps = conn.prepareStatement(sql);
    	         ResultSet rs = ps.executeQuery()) {
    	        if (rs.next()) {
    	            String lastId = rs.getString("orderNo"); 
    	            int num = Integer.parseInt(lastId.substring(1)); 
    	            num++;
    	            return prefix + String.format("%04d", num); 
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	    return "O0001"; // 如果資料庫是空的，從 O0001 開始
    	}

    @Override
    public List<Porder> getAllOrders() {
        List<Porder> list = new ArrayList<>();
        String sql = "SELECT * FROM porder";
        try (
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Porder o = new Porder();
                o.setOrderNo(rs.getString("orderNo"));
                o.setCustomerId(rs.getString("customerId"));
                o.setProductno(rs.getString("productno"));
                o.setAmount(rs.getInt("amount"));
                list.add(o);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public List<Porder> getOrdersByCustomer(String customerId) {
        List<Porder> orders = new ArrayList<>();
        String sql = "SELECT * FROM porder WHERE customerId = ? ORDER BY id DESC";
        
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Porder o = new Porder();
                    o.setId(rs.getInt("id"));
                    o.setOrderNo(rs.getString("orderNo"));
                    o.setCustomerId(rs.getString("customerId"));
                    o.setProductno(rs.getString("productno"));
                    o.setAmount(rs.getInt("amount"));
                    orders.add(o);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return orders;
    }

    @Override
    public int updateOrder(Porder order) {
        String sql = "UPDATE porder SET customerId=?, productno=?, amount=? WHERE orderNo=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, order.getCustomerId());
            ps.setString(2, order.getProductno());
            ps.setInt(3, order.getAmount());
            ps.setString(4, order.getOrderNo());

            int rows = ps.executeUpdate(); 
            if(!conn.getAutoCommit()) {
                conn.commit();
            }
            System.out.println("更新的列數: " + rows + " | OrderNo=" + order.getOrderNo());
            return rows;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void deleteOrder(String orderNo) {
        String sql = "DELETE FROM porder WHERE orderNo=?";
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, orderNo);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public int getTotalSalesByProduct(String productno) {
        int total = 0;
        String sql = "SELECT SUM(amount) AS total FROM porder WHERE productno=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, productno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

	@Override
	public List<Porder> getOrdersByProductNo(String productno) {
	    List<Porder> list = new ArrayList<>();
	    String sql = "SELECT * FROM porder WHERE productno = ?";
	    try (
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, productno);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	        	Porder o = new Porder();
                o.setId(rs.getInt("id"));
                o.setOrderNo(rs.getString("orderNo"));
                o.setCustomerId(rs.getString("customerId"));
                o.setProductno(rs.getString("productno"));
                o.setAmount(rs.getInt("amount"));
                list.add(o);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}

