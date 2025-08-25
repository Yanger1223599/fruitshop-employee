package controller.employee;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.LoginEmployee;
import dao.impl.EmployeeDaoImpl;
import model.Employee;

public class AddEmployee extends JFrame {
    private JTextField txtName, txtUsername;
    private JPasswordField txtPassword;
    private JButton btnRegister;

    private EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

    public AddEmployee() {
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JLabel lblName = new JLabel("姓名:");
        lblName.setBounds(61, 37, 44, 21);
        txtName = new JTextField();
        txtName.setBounds(134, 37, 113, 21);

        JLabel lblUsername = new JLabel("帳號:");
        lblUsername.setBounds(61, 86, 44, 21);
        txtUsername = new JTextField();
        txtUsername.setBounds(134, 86, 113, 21);

        JLabel lblPassword = new JLabel("密碼:");
        lblPassword.setBounds(61, 138, 44, 21);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(134, 138, 113, 21);

        btnRegister = new JButton("註冊");
        btnRegister.setBounds(134, 181, 94, 23);
        panel.setLayout(null);

        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        JLabel label = new JLabel("");
        label.setBounds(261, 79, 113, 28);
        panel.add(label); // 佔位
        panel.add(btnRegister);

        getContentPane().add(panel, BorderLayout.CENTER);
        
        JButton btnNewButton = new JButton("返回");
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		LoginEmployee loginEmployee = new LoginEmployee();
				loginEmployee.setVisible(true);
				dispose();
        	}
        });
        btnNewButton.setBounds(134, 214, 94, 23);
        panel.add(btnNewButton);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerEmployee();
            }
        });
    }

    private void registerEmployee() {
        String name = txtName.getText().trim();
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "請填寫完整資料", "錯誤", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Employee emp = new Employee();
        emp.setName(name);
        emp.setUsername(username);
        emp.setPassword(password);

        boolean success = employeeDao.register(emp);

        if (success) {
            JOptionPane.showMessageDialog(this, 
                "註冊成功！系統分配的員工編號是：" + emp.getEmployeeNo(),
                "成功", JOptionPane.INFORMATION_MESSAGE);
            
            LoginEmployee loginEmployee = new LoginEmployee();
            loginEmployee.setVisible(true);
            dispose(); 
            
        } else {
            JOptionPane.showMessageDialog(this, "註冊失敗，帳號可能已存在", "錯誤", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AddEmployee().setVisible(true);
        });
    }
}