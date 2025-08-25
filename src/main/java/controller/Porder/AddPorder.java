package controller.Porder;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

import javax.swing.*;

import dao.impl.CustomerDaoImpl;
import dao.impl.PorderDaoImpl;
import dao.impl.ProductDaoImpl;
import model.Customer;
import model.Porder;
import model.Product;
import util.Tool;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddPorder extends JFrame {

    private static final long serialVersionUID = 1L;
    private JComboBox<Product> productCombo;
    private JTextField txtAmount;
    private JLabel lblPrice;
    private JLabel lblTotal;
    private JButton btnCheckout;

    private ProductDaoImpl productDao = new ProductDaoImpl();
    private PorderDaoImpl orderDao = new PorderDaoImpl();

    private Customer loginCustomer;

    private Connection conn;
    private JButton btnNewButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Customer c = (Customer) Tool.readFile("customer.txt");
                AddPorder frame = new AddPorder();
                frame.setLoginCustomer(c);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AddPorder() {
        setTitle("新增訂單/結帳");
        setSize(400, 347);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label = new JLabel("商品:");
        label.setBounds(69, 51, 51, 34);
        panel.add(label);

        productCombo = new JComboBox<>();
        productCombo.setBounds(122, 51, 187, 34);
        loadProducts();
        panel.add(productCombo);
        
        JLabel lblNewLabel = new JLabel("備註: 填完數量要按一下要購買的商品小計才會計算");
        lblNewLabel.setBounds(53, 217, 273, 28);
        panel.add(lblNewLabel);


        JLabel label_1 = new JLabel("數量:");
        label_1.setBounds(69, 94, 44, 34);
        panel.add(label_1);

        txtAmount = new JTextField();
        txtAmount.setBounds(122, 95, 187, 34);
        panel.add(txtAmount);

        JLabel label_2 = new JLabel("單價:");
        label_2.setBounds(69, 133, 51, 34);
        panel.add(label_2);

        lblPrice = new JLabel("0");
        lblPrice.setBounds(197, 139, 81, 23);
        panel.add(lblPrice);

        JLabel label_3 = new JLabel("小計:");
        label_3.setBounds(69, 163, 51, 34);
        panel.add(label_3);

        lblTotal = new JLabel("0");
        lblTotal.setBounds(197, 163, 69, 34);
        panel.add(lblTotal);

        btnCheckout = new JButton("結帳");
        btnCheckout.setBounds(222, 275, 87, 23);
        panel.add(btnCheckout);

        productCombo.addActionListener(e -> updatePrice());
        txtAmount.addActionListener(e -> updateTotal());
        btnCheckout.addActionListener(new CheckoutHandler());

        getContentPane().add(panel);
        
        btnNewButton = new JButton("返回");
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		PorderMangerCustomer pordermangerCustomer=new PorderMangerCustomer();
				pordermangerCustomer.setVisible(true);
				dispose();
        	}
        });
        btnNewButton.setBounds(69, 275, 87, 23);
        panel.add(btnNewButton);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(AddPorder.class.getResource("/controller/1-2406191J6230-L.jpg")));
        lblNewLabel_1.setBounds(0, 0, 384, 308);
        panel.add(lblNewLabel_1);
        
  
        
     
    }

    private void loadProducts() {
        List<Product> products = productDao.getAll();
        for (Product p : products) {
            productCombo.addItem(p);
        }
        productCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Product) {
                    Product p = (Product) value;
                    setText(p.getName() + " ($" + p.getPrice() + ")");
                }
                return this;
            }
        });
    }

    private void updatePrice() {
        Product p = (Product) productCombo.getSelectedItem();
        if (p != null) {
            lblPrice.setText(String.valueOf(p.getPrice()));
            updateTotal();
        }
    }

    private void updateTotal() {
        try {
            int amount = Integer.parseInt(txtAmount.getText());
            int price = Integer.parseInt(lblPrice.getText());
            lblTotal.setText(String.valueOf(amount * price));
        } catch (Exception e) {
            lblTotal.setText("0");
        }
    }

    public void setLoginCustomer(Customer customer) {
        this.loginCustomer = customer;
    }

 
    
    
    

    private class CheckoutHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Product p = (Product) productCombo.getSelectedItem();
                int amount = Integer.parseInt(txtAmount.getText());

                if (p == null) {
                    JOptionPane.showMessageDialog(null, "請選擇商品！");
                    return;
                }

                if (loginCustomer == null) {
                    JOptionPane.showMessageDialog(null, "未登入，無法結帳！");
                    return;
                }

                // 確保 customerId 不為 null
                CustomerDaoImpl customerDao = new CustomerDaoImpl();
                String customerId = customerDao.ensureCustomerId(loginCustomer.getUsername());
                loginCustomer.setCustomerid(customerId);

                Porder order = new Porder();
                order.setCustomerId(loginCustomer.getCustomerid());
                order.setProductno(p.getProductno());
                order.setAmount(amount);

                orderDao.addOrder(order);

                JOptionPane.showMessageDialog(null, " 訂單結帳成功！");
                
                FindAllPorderCustomer findallporderCustomer = new FindAllPorderCustomer(loginCustomer);
                findallporderCustomer.setVisible(true);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, " 結帳失敗: " + ex.getMessage());
            }
            
            

        }
    }
}
