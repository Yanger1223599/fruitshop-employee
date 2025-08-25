package controller.Porder;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LoginCustomer;
import model.Customer;
import util.Tool;
import dao.impl.CustomerDaoImpl;

public class PorderMangerCustomer extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PorderMangerCustomer frame = new PorderMangerCustomer();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public PorderMangerCustomer() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel showMember2 = new JLabel("");
        showMember2.setFont(new Font("新細明體", Font.PLAIN, 16));
        showMember2.setBounds(168, 35, 200, 36);
        contentPane.add(showMember2);

        // 讀取登入的 Customer
        Customer c = (Customer) Tool.readFile("customer.txt");

        // ✅ 確保 customerId 不為空
        if (c.getCustomerid() == null || c.getCustomerid().isEmpty()) {
            CustomerDaoImpl customerDao = new CustomerDaoImpl();
            String generatedId = customerDao.ensureCustomerId(c.getUsername());
            c.setCustomerid(generatedId);
            // 更新回 customer.txt
            Tool.saveFile(c, "customer.txt");
        }

        String show2 = c.getName() + " 歡迎您";
        showMember2.setText(show2);

        /************************************************************************/

        // 查詢訂單按鈕
        JButton findAllPorder = new JButton("查詢訂單");
        findAllPorder.setBounds(168, 147, 100, 23);
        findAllPorder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FindAllPorderCustomer findallporderCustomer = new FindAllPorderCustomer(c);
                findallporderCustomer.setVisible(true);
                dispose();
            }
        });
        contentPane.add(findAllPorder);

        // 挑選商品按鈕
        JButton addPorder = new JButton("挑選商品");
        addPorder.setBounds(168, 97, 100, 23);
        addPorder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddPorder addPorderFrame = new AddPorder();
                addPorderFrame.setLoginCustomer(c); // ✅ 傳入登入資訊
                addPorderFrame.setVisible(true);
                dispose();
            }
        });
        contentPane.add(addPorder);

        // 登出按鈕
        JButton btnLogout = new JButton("登出");
        btnLogout.setBounds(168, 193, 100, 23);
        btnLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginCustomer loginCustomer = new LoginCustomer();
                loginCustomer.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnLogout);
        
        
        
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PorderMangerCustomer.class.getResource("/controller/546dkmfadnvs.jpg")));
		lblNewLabel.setBounds(0, 0, 434, 261);
		contentPane.add(lblNewLabel);
    }
    

}

