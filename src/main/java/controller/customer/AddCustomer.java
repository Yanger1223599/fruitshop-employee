package controller.customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LoginCustomer;
import model.Customer;
import service.impl.CustomerServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class AddCustomer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField customerid;
	private JTextField name;
	private JTextField username;
	private JTextField password;
	private JTextField address;
	private JTextField phone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCustomer frame = new AddCustomer();
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
	public AddCustomer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("名字");
		lblNewLabel.setBounds(124, 50, 46, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("帳號");
		lblNewLabel_1.setBounds(124, 82, 46, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("密碼");
		lblNewLabel_2.setBounds(124, 113, 46, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("地址");
		lblNewLabel_3.setBounds(124, 147, 46, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("電話");
		lblNewLabel_4.setBounds(124, 180, 46, 15);
		contentPane.add(lblNewLabel_4);
		
		name = new JTextField();
		name.setBounds(180, 47, 126, 21);
		contentPane.add(name);
		name.setColumns(10);
		
		username = new JTextField();
		username.setBounds(180, 79, 126, 21);
		contentPane.add(username);
		username.setColumns(10);
		
		password = new JTextField();
		password.setBounds(180, 110, 126, 21);
		contentPane.add(password);
		password.setColumns(10);
		
		address = new JTextField();
		address.setBounds(180, 144, 126, 21);
		contentPane.add(address);
		address.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(180, 177, 126, 21);
		contentPane.add(phone);
		phone.setColumns(10);
		

		JLabel errormessage = new JLabel("");
		errormessage.setFont(new Font("新細明體", Font.PLAIN, 14));
		errormessage.setBounds(294, 224, 118, 23);
		contentPane.add(errormessage);

		
		
		/*********************************************************************/
		
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String Name = name.getText();
				String Username = username.getText();
				String Password = password.getText();
				String Address = address.getText();
				String Phone = phone.getText();
				if(Name.isEmpty() ||Username.isEmpty() || Password.isEmpty() || Address.isEmpty()||Phone.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "失敗","失敗",JOptionPane.INFORMATION_MESSAGE);
					
				}
				else 
				{
					Customer customer = new Customer(Name,Username,Password,Address,Phone);
					
					if(new CustomerServiceImpl().addCustomer(customer))
					{

						AddCustomerSuccess addmembersuccess = new AddCustomerSuccess();
						addmembersuccess.setVisible(true);
						dispose();
						
					}
					else
					{
						errormessage.setText("帳號重複");
					}
				}
				
				
				
			}
		});
		btnNewButton.setBounds(172, 224, 96, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("返回");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				LoginCustomer login = new LoginCustomer();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(10, 224, 87, 23);
		contentPane.add(btnNewButton_1);
		
		
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(AddCustomer.class.getResource("/controller/abstract-shimmery-stars-pastel-background.jpg")));
		lblNewLabel_5.setBounds(0, 0, 434, 261);
		contentPane.add(lblNewLabel_5);
		
		
	}

}
