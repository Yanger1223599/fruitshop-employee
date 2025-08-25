package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class LoginError extends JFrame {

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
					LoginError frame = new LoginError();
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
	public LoginError() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("無此帳戶");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 46));
		lblNewLabel.setBounds(118, 29, 257, 110);
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
		
		
		/**********************************************************************/
		
		
		
		
		JButton btnNewButton_1 = new JButton("返回");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timer.stop();
				Choose choose = new Choose();
				choose.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(163, 204, 87, 23);
		contentPane.add(btnNewButton_1);


		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LoginError.class.getResource("/controller/istockphoto-1359269986-612x612.jpg")));
		lblNewLabel_1.setBounds(0, 0, 434, 261);
		contentPane.add(lblNewLabel_1);


	}

	protected void openNextWindow() {
		Choose choose = new Choose();
		choose.setVisible(true);
		dispose();
		
	}
}
