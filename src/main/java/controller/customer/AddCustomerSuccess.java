package controller.customer;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import controller.LoginCustomer;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class AddCustomerSuccess extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel dfs;
    private int seconds = 5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCustomerSuccess frame = new AddCustomerSuccess();
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
	public AddCustomerSuccess() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("註冊成功");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 30));
		lblNewLabel.setBounds(152, 72, 172, 45);
		contentPane.add(lblNewLabel);


		

		
		        

		dfs = new JLabel("倒數: " + seconds + " 秒", SwingConstants.CENTER);
		dfs.setBounds(152, 127, 117, 45);
		dfs.setFont(new Font("新細明體", Font.PLAIN, 24));
        getContentPane().add(dfs);
        // Timer 每秒執行一次
        Timer timer = new Timer(1000, new ActionListener() {

        public void actionPerformed(ActionEvent e) {
        	seconds--;
        	dfs.setText("倒數: " + seconds + " 秒");

        	if (seconds <= 0) {
        		((Timer) e.getSource()).stop(); // 停止計時器
        		openNextWindow();
        	}
        }
        });
        timer.start();
		    
	
		
		
		/********************************************************************/
		
		
		JButton btnNewButton = new JButton("返回登入畫面");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timer.stop();
				LoginCustomer login = new LoginCustomer();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(152, 204, 125, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(AddCustomerSuccess.class.getResource("/controller/0V8ItkfwFH.jpg")));
		lblNewLabel_1.setBounds(0, 0, 434, 261);
		contentPane.add(lblNewLabel_1);	

	}
	private void openNextWindow() {
		LoginCustomer login = new LoginCustomer();
		login.setVisible(true);// 開新視窗
        dispose();        // 關閉目前視窗
    }
}