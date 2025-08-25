
package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.ProductDao;
import model.Product;
import util.DbConnection;

public class ProductDaoImpl implements ProductDao {
	
	Connection conn = DbConnection.getDb();

    @Override
    public Product findByNo(String productNo) {
        Product p = null;
        String sql = "SELECT * FROM product WHERE productno=?";
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, productNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new Product();
                p.setId(rs.getInt("id"));
                p.setProductno(rs.getString("productno"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getInt("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setProductno(rs.getString("productno"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getInt("price"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public void addProduct(Product productNo) {
        String newProductNo = generateProductNo();
        String sql = "INSERT INTO product(name, price) VALUES (?, ?)";
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	
            ps.setString(1, productNo.getName());
            ps.setInt(2, productNo.getPrice());
            ps.executeUpdate();
            System.out.println("✅ 商品新增成功: " + newProductNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO product (productno, name, price) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getProductno());  
            ps.setString(2, product.getName());
            ps.setInt(3, product.getPrice());
            ps.executeUpdate();
            System.out.println("商品新增成功: " + product.getProductno());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* 自動生成 productno */
    public String generateProductNo() {
        String prefix = "P";
        String sql = "SELECT productno FROM product ORDER BY id DESC LIMIT 1";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String lastId = rs.getString("productno"); // P007
                int num = Integer.parseInt(lastId.substring(1)); // 取 007 -> 7
                num++;
                return prefix + String.format("%03d", num); // P008
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "P001"; // 如果資料庫是空的，從 P001 開始
    }

    @Override
    public void delete(String productNo) {
        String sql = "DELETE FROM product WHERE productno=?";
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, productNo);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

}
