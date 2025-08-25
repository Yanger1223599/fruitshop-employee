package controller.employee;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import dao.impl.PorderDaoImpl;
import model.Product;
import model.Porder;
import service.EmployeeService;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductList extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private ProductDao productDao = new ProductDaoImpl();
    
    private JButton btnDelete = new JButton("刪除選取商品");

    private EmployeeService employeeService;

    public ProductList(EmployeeService service) {
        this.employeeService = service;
        setTitle("商品清單管理");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tableModel = new DefaultTableModel(new Object[]{"商品編號", "商品名稱", "價格"}, 0);
        getContentPane().setLayout(null);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 540, 250);
        getContentPane().add(scrollPane);

        JButton btnAdd = new JButton("新增商品");
        btnAdd.setBounds(227, 300, 120, 30);
        getContentPane().add(btnAdd);

        btnDelete.setBounds(390, 300, 120, 30);
        getContentPane().add(btnDelete);

        JButton btnBack = new JButton("返回");
        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EmployeeManger employeemanger = new EmployeeManger(service);
                employeemanger.setVisible(true);
                dispose();
            }
        });
        btnBack.setBounds(63, 300, 120, 30);
        getContentPane().add(btnBack);

        // 新增商品
        btnAdd.addActionListener(e -> {
            AddProduct addProduct = new AddProduct(service);
            addProduct.setVisible(true);
            dispose();
        });

        // 刪除商品
        btnDelete.addActionListener(e -> deleteSelectedProduct());

        refreshTable();
    }

    // 刷新 JTable
    public void refreshTable() {
        tableModel.setRowCount(0);
        List<Product> products = productDao.getAll();
        for (Product p : products) {
            tableModel.addRow(new Object[]{p.getProductno(), p.getName(), p.getPrice()});
        }
    }

    // 刪除選取商品
    private void deleteSelectedProduct() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "請先選擇一個商品！");
            return;
        }

        String productno = tableModel.getValueAt(row, 0).toString();

        // 檢查是否已有訂單使用該商品
        List<Porder> orders = new PorderDaoImpl().getOrdersByProductNo(productno);
        if (!orders.isEmpty()) {
            JOptionPane.showMessageDialog(this, "該商品已有訂單紀錄，無法刪除！");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "確定要刪除此商品嗎？", "確認刪除", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            productDao.delete(productno);
            JOptionPane.showMessageDialog(this, "商品刪除成功！");
            refreshTable();
        }
    }
}

