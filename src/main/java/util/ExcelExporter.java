package util;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Porder;

import java.io.FileOutputStream;
import java.util.List;

public class ExcelExporter {

    public static void exportOrders(List<Porder> orders, String fileName) {
        try (Workbook wb = new XSSFWorkbook()) {
            Sheet sheet = wb.createSheet("Orders");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("OrderNo");
            header.createCell(1).setCellValue("CustomerId");
            header.createCell(2).setCellValue("ProductNo");
            header.createCell(3).setCellValue("Amount");

            int rowIdx = 1;
            for (Porder o : orders) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(o.getOrderNo());
                row.createCell(1).setCellValue(o.getCustomerId());
                row.createCell(2).setCellValue(o.getProductno());
                row.createCell(3).setCellValue(o.getAmount());
            }

            FileOutputStream fos = new FileOutputStream(fileName);
            wb.write(fos);
            fos.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}