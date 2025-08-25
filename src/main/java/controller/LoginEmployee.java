package controller;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.employee.AddEmployee;
import controller.employee.EmployeeController;
import controller.employee.EmployeeManger;
import controller.employee.ForgetPasswordEmployee;
import model.Employee;
import service.EmployeeService;
import service.impl.EmployeeServiceImpl;
import util.Tool;
import javax.swing.ImageIcon;

public class LoginEmployee extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField username;
    private JTextField password;


    private EmployeeService employeeService = new EmployeeServiceImpl();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginEmployee frame = new LoginEmployee();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginEmployee() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 377, 254);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("員工");
        lblTitle.setFont(new Font("新細明體", Font.PLAIN, 20));
        lblTitle.setBounds(142, 10, 69, 32);
        contentPane.add(lblTitle);

        JLabel lblUsername = new JLabel("帳號");
        lblUsername.setBounds(54, 55, 46, 15);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("密碼");
        lblPassword.setBounds(54, 106, 46, 15);
        contentPane.add(lblPassword);

        username = new JTextField();
        username.setBounds(127, 52, 96, 21);
        contentPane.add(username);
        username.setColumns(10);

        password = new JTextField();
        password.setBounds(127, 103, 96, 21);
        contentPane.add(password);
        password.setColumns(10);

        // 登入按鈕
        JButton btnLogin = new JButton("登入");
        btnLogin.setBounds(250, 49, 87, 23);
        contentPane.add(btnLogin);

        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String user = username.getText();
                String pass = password.getText();
                Employee em = employeeService.login(user, pass);
                if (em != null) {
                    Tool.saveFile(em, "employee.txt");
                    EmployeeManger employeeManger = new EmployeeManger(employeeService);
                    employeeManger.setVisible(true);
                    dispose();
                } else {
                    LoginError loginError = new LoginError();
                    loginError.setVisible(true);
                    dispose();
                }
            }
        });

        // 註冊按鈕
        JButton btnRegister = new JButton("註冊");
        btnRegister.setBounds(127, 142, 96, 23);
        contentPane.add(btnRegister);

        btnRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddEmployee addEmployee = new AddEmployee();
                addEmployee.setVisible(true);
                dispose();
            }
        });

        // 忘記密碼
        JButton btnForget = new JButton("忘記密碼");
        btnForget.setBounds(250, 102, 87, 23);
        contentPane.add(btnForget);

        btnForget.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ForgetPasswordEmployee forgetPassword = new ForgetPasswordEmployee();
                forgetPassword.setVisible(true);
                dispose();
            }
        });

        // 返回
        JButton btnBack = new JButton("返回");
        btnBack.setBounds(127, 175, 96, 23);
        contentPane.add(btnBack);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(LoginEmployee.class.getResource("/controller/shimmering-gold-moon-stars-pattern-watercolor.jpg")));
        lblNewLabel.setBounds(0, 0, 361, 215);
        contentPane.add(lblNewLabel);

        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Choose choose = new Choose();
                choose.setVisible(true);
                dispose();
            }
        });
    }
}
