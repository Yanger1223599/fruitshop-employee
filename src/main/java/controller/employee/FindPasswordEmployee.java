package controller.employee;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LoginEmployee;
import model.Employee;
import util.Tool;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class FindPasswordEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindPasswordEmployee frame = new FindPasswordEmployee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Employee employee=(Employee)Tool.readFile("employee.txt");

	/**
	 * Create the frame.
	 */
	public FindPasswordEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 294);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea showReporter = new JTextArea();
		showReporter.setBackground(new Color(255, 247, 232));
		showReporter.setBounds(93, 50, 231, 146);
		contentPane.add(showReporter);
		String show="編號:  "+employee.getEmployeeNo()+"\n名字:  "+employee.getName()+"\n帳號:  "+employee.getUsername()+"\n密碼:  "+employee.getPassword();
				
		
		
		showReporter.setText(show);
		
		JButton btnNewButton = new JButton("返回登入介面");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				LoginEmployee loginEmployee = new LoginEmployee();
				loginEmployee.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton.setBounds(152, 228, 117, 23);
		contentPane.add(btnNewButton);
		
		
	}
}
