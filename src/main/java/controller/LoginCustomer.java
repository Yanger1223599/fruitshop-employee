package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Porder.PorderMangerCustomer;
import controller.customer.AddCustomer;
import controller.customer.ForgetPassword;
import model.Customer;
import service.impl.CustomerServiceImpl;
import util.Tool;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;

public class LoginCustomer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginCustomer frame = new LoginCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginCustomer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 377, 254);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("水果店");
		lblNewLabel_3.setFont(new Font("新細明體", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(142, 10, 69, 32);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("帳號");
		lblNewLabel.setBounds(54, 55, 46, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密碼");
		lblNewLabel_1.setBounds(54, 106, 46, 15);
		contentPane.add(lblNewLabel_1);
		
		username = new JTextField();
		username.setBounds(127, 52, 96, 21);
		contentPane.add(username);
		username.setColumns(10);
		
		password = new JTextField();
		password.setBounds(127, 103, 96, 21);
		contentPane.add(password);
		password.setColumns(10);
		
		
		/********************************************************/
		
		
		JButton btnNewButton = new JButton("登入");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				
				String Username = username.getText();
				String Password = password.getText();
				
				Customer c=new CustomerServiceImpl().login(Username, Password);
				
				if(c!=null)
				{
					Tool.saveFile(c,"customer.txt");
					PorderMangerCustomer pordermangercustomer=new PorderMangerCustomer();
					pordermangercustomer.setVisible(true);
					dispose();
				}
				else
				{
					LoginError loginerror = new LoginError();
					loginerror.setVisible(true);
					dispose();
				}
				
			}
		});
		btnNewButton.setBounds(250, 49, 87, 23);
		contentPane.add(btnNewButton);
		
		
		
		JButton btnNewButton_1 = new JButton("註冊");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				AddCustomer addcustomer = new AddCustomer();
				addcustomer.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_1.setBounds(127, 142, 96, 23);
		contentPane.add(btnNewButton_1);
		
	
		
		JButton btnNewButton_3 = new JButton("忘記密碼");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ForgetPassword forgetpassword = new ForgetPassword();
				forgetpassword.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setBounds(250, 102, 87, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("返回");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Choose choose  = new Choose ();
				choose .setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(0, 192, 87, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(LoginCustomer.class.getResource("/controller/pngtree-pink-yellow-clouds-cute-baby-background-image_587296.jpg")));
		lblNewLabel_2.setBounds(0, 0, 367, 215);
		contentPane.add(lblNewLabel_2);
		
		

	}
}
