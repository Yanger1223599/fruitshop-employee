package controller.customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LoginCustomer;
import model.Customer;
import util.Tool;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class FindPasswordCustomer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindPasswordCustomer frame = new FindPasswordCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Customer Customer=(Customer)Tool.readFile("customer.txt");

	/**
	 * Create the frame.
	 */
	public FindPasswordCustomer() {
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
		String show="名字:  "+Customer.getName()+"\n帳號:  "+Customer.getUsername()+"\n密碼:  "+Customer.getPassword();
				
		
		
		showReporter.setText(show);
		
		JButton btnNewButton = new JButton("返回登入介面");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				LoginCustomer login = new LoginCustomer();
				login.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton.setBounds(152, 228, 117, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FindPasswordCustomer.class.getResource("/controller/44231b7065eb27565eaa362807a61e22.jpg")));
		lblNewLabel.setBounds(0, 0, 434, 255);
		contentPane.add(lblNewLabel);
	}
}
