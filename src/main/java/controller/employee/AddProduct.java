package controller.employee;


import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import model.Product;
import service.EmployeeService;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddProduct extends JFrame {
    private JTextField txtName;
    private JTextField txtPrice;
    private ProductDao productDao = new ProductDaoImpl();

    private EmployeeService employeeService;

    public AddProduct(EmployeeService service) {
    	 this.employeeService = service;

        setTitle("新增商品");
        setBounds(100, 100, 420, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblName = new JLabel("商品名稱：");
        lblName.setBounds(50, 40, 100, 25);
        getContentPane().add(lblName);

        txtName = new JTextField();
        txtName.setBounds(150, 40, 150, 25);
        getContentPane().add(txtName);

        JLabel lblPrice = new JLabel("單價：");
        lblPrice.setBounds(50, 80, 100, 25);
        getContentPane().add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(150, 80, 150, 25);
        getContentPane().add(txtPrice);

        JButton btnSave = new JButton("新增商品");
        btnSave.setBounds(218, 130, 107, 30);
        getContentPane().add(btnSave);
        // 儲存事件
        btnSave.addActionListener(e -> saveProduct());
        
        JButton btnCancel = new JButton("取消");
        btnCancel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		EmployeeManger employeemanger=new EmployeeManger(service);
        		employeemanger.setVisible(true);
				dispose();
        	}
        });
        btnCancel.setBounds(95, 130, 107, 30);
        getContentPane().add(btnCancel);
    }

    private void saveProduct() {
        String name = txtName.getText().trim();
        String priceStr = txtPrice.getText().trim();

        if (name.isEmpty() || priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "請輸入完整資料！");
            return;
        }

        try {
            int price = Integer.parseInt(priceStr);
            String productno = ((ProductDaoImpl) productDao).generateProductNo();
            Product product = new Product(productno, name, price);

            productDao.save(product);
            JOptionPane.showMessageDialog(this, "商品新增成功！ 商品編號：" + productno);
            
           
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "價格必須是數字！");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "錯誤：" + e.getMessage());
        }
    }
}