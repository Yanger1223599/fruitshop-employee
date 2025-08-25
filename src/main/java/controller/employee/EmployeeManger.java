package controller.employee;


import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LoginEmployee;
import model.Employee;
import service.EmployeeService;
import util.Tool;
import javax.swing.ImageIcon;

public class EmployeeManger extends JFrame {
    private JPanel contentPane;
    private EmployeeService employeeService;

    public EmployeeManger(EmployeeService service) {
        this.employeeService = service;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel showMember2 = new JLabel("");
        showMember2.setFont(new Font("新細明體", Font.PLAIN, 16));
        showMember2.setBounds(150, 32, 200, 30);
        contentPane.add(showMember2);

        Employee em = (Employee) Tool.readFile("employee.txt");
        if (em != null) {
            showMember2.setText(em.getName() + " 歡迎您");
        }

        JButton findAllPorder = new JButton("查詢訂單");
        findAllPorder.setBounds(150, 162, 120, 30);
        contentPane.add(findAllPorder);

        findAllPorder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EmployeeController employeeController = new EmployeeController(employeeService);
                employeeController.setVisible(true);
                dispose();
            }
        });

        JButton btnLogout = new JButton("登出");
        btnLogout.setBounds(150, 202, 120, 30);
        contentPane.add(btnLogout);
        
        JButton btnNewButton = new JButton("新增商品");
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		AddProduct addproduct = new AddProduct(service);
        		addproduct.setVisible(true);
                dispose();
        	}
        });
        btnNewButton.setBounds(150, 118, 120, 34);
        contentPane.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("所有商品");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		ProductList productlist = new ProductList(service);
        		productlist.setVisible(true);
                dispose();
        		
        	}
        });
        btnNewButton_1.setBounds(150, 74, 120, 34);
        contentPane.add(btnNewButton_1);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(EmployeeManger.class.getResource("/controller/iPca05wKd7.jpg")));
        lblNewLabel.setBounds(0, 0, 434, 261);
        contentPane.add(lblNewLabel);

        btnLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginEmployee loginEmployee = new LoginEmployee();
                loginEmployee.setVisible(true);
                dispose();
            }
        });
    }
}
