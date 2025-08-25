package controller.Porder;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import dao.impl.PorderDaoImpl;
import dao.impl.ProductDaoImpl;
import model.Customer;
import model.Porder;
import util.Tool;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FindAllPorderCustomer extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel tableModel;

    private PorderDaoImpl orderDao = new PorderDaoImpl();
    private ProductDaoImpl productDao = new ProductDaoImpl(); // 用來查商品名稱

    private Customer loginCustomer; // 登入的顧客

    // 🔹 建構子直接傳入 Customer
    public FindAllPorderCustomer(Customer customer) {
        this.loginCustomer = customer;

        setTitle("查看我的訂單");
        setSize(600, 543);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new Object[]{"訂單ID", "訂單編號", "商品名稱", "數量"}, 0);
        table = new JTable(tableModel);
        loadOrders(); // 初始化就載入訂單

        JPanel panel = new JPanel();
        panel.setBounds(0, 503, 584, 1);
        panel.setLayout(null);
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 584, 453);
        getContentPane().add(scrollPane);
        getContentPane().add(panel);
                
        JButton btnNewButton = new JButton("返回");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PorderMangerCustomer pordermangerCustomer = new PorderMangerCustomer();
                pordermangerCustomer.setVisible(true);
                dispose();
            }
        });
        btnNewButton.setBounds(234, 470, 87, 23);
        getContentPane().add(btnNewButton);
    }

    /** 載入顧客的訂單 */
    private void loadOrders() {
        if (loginCustomer == null) {
            JOptionPane.showMessageDialog(this, "未登入，無法查看訂單！");
            return;
        }

        tableModel.setRowCount(0); // 清空表格

        try {
            List<Porder> orders = orderDao.getOrdersByCustomer(loginCustomer.getCustomerid());
            for (Porder o : orders) {
                String productName = o.getProductno(); // 預設 fallback
                try {
                    productName = productDao.findByNo(o.getProductno()).getName(); // 查商品名稱
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                tableModel.addRow(new Object[]{
                        o.getId(),
                        o.getOrderNo(),
                        productName, // 顯示商品名稱
                        o.getAmount()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "讀取訂單失敗: " + e.getMessage());
        }
    }

    // 🔹 測試用 main
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Customer c = (Customer) Tool.readFile("customer.txt"); // 讀取登入顧客
                FindAllPorderCustomer frame = new FindAllPorderCustomer(c);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
