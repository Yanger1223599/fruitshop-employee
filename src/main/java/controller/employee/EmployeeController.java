package controller.employee;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Porder;
import service.EmployeeService;
import util.ExcelExporter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeeController extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnUpdate = new JButton("修改選取訂單");
    private JButton btnDelete = new JButton("刪除選取訂單");
    private JButton btnExport = new JButton("匯出 Excel");

    private EmployeeService employeeService;
    private final JButton btnNewButton = new JButton("返回");

    public EmployeeController(EmployeeService service) {
        this.employeeService = service;

        setSize(600, 510);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tableModel = new DefaultTableModel(new Object[]{"OrderNo", "CustomerId", "ProductNo", "Amount"}, 0);
        table = new JTable(tableModel);
        loadOrders();

        JPanel btnPanel = new JPanel();
        btnPanel.setBounds(0, 428, 584, 33);
        btnPanel.setLayout(null);
        btnUpdate.setBounds(157, 5, 127, 23);
        btnPanel.add(btnUpdate);
        btnDelete.setBounds(294, 5, 127, 23);
        btnPanel.add(btnDelete);
        btnExport.setBounds(431, 5, 105, 23);
        btnPanel.add(btnExport);
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 584, 428);
        getContentPane().add(scrollPane);
        getContentPane().add(btnPanel);
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		EmployeeManger employeemanger=new EmployeeManger(service);
        		employeemanger.setVisible(true);
				dispose();
        		
        		
        	}
        });
        btnNewButton.setBounds(42, 5, 105, 23);
        
        btnPanel.add(btnNewButton);

        // 修改訂單
        btnUpdate.addActionListener(e -> updateSelectedOrder());

        // 刪除訂單
        btnDelete.addActionListener(e -> deleteSelectedOrder());

        // 匯出 Excel
        btnExport.addActionListener(e -> exportOrders());

        setLocationRelativeTo(null);
    }

    private void loadOrders() {
        tableModel.setRowCount(0);
        List<Porder> orders = employeeService.getAllOrders();
        for (Porder o : orders) {
            tableModel.addRow(new Object[]{o.getOrderNo(), o.getCustomerId(), o.getProductno(), o.getAmount()});
        }
    }

    private void updateSelectedOrder() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "請選取一筆訂單！");
            return;
        }
        try {
            String orderNo = (String) tableModel.getValueAt(row, 0);
            String customerId = (String)tableModel.getValueAt(row, 1);
            String productNo = (String) tableModel.getValueAt(row, 2);
            int amount = Integer.parseInt(tableModel.getValueAt(row, 3).toString());

            Porder o = new Porder();
            o.setOrderNo(orderNo);
            o.setCustomerId(customerId);    
            o.setProductno(productNo);
            o.setAmount(amount);

            boolean success = employeeService.updateOrder(o);
            if(success) {
                JOptionPane.showMessageDialog(null, "訂單修改成功！");
                loadOrders(); // 重新載入表格
            } else {
                JOptionPane.showMessageDialog(null, "修改失敗：找不到對應訂單！");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "修改失敗：" + ex.getMessage());
        }
    }


    private void deleteSelectedOrder() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "請選取一筆訂單！");
            return;
        }
        // 改成取 OrderNo (String)
        String orderNo = (String) tableModel.getValueAt(row, 0);
        employeeService.deleteOrder(orderNo);
        tableModel.removeRow(row);
        JOptionPane.showMessageDialog(null, "訂單已刪除！");
    }

    private void exportOrders() {
        List<Porder> orders = employeeService.getAllOrders();
        ExcelExporter.exportOrders(orders, "orders.xlsx");
        JOptionPane.showMessageDialog(null, " 已匯出到 orders.xlsx！");
    }
}
