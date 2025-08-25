# Inventory & Order Management System

這是一個基於 **Java (Swing + Maven + MySQL)** 的進銷訂單管理系統，支援會員、員工、產品與訂單管理，並提供 **Excel 匯出** 功能。

---

## 📂 專案結構
```
controller
 ├─ Choose.java
 ├─ LoginCustomer.java
 ├─ LoginEmployee.java
 ├─ LoginError.java
 ├─ customer/
 │    ├─ AddCustomer.java
 │    ├─ AddCustomerSuccess.java
 │    ├─ FindPasswordCustomer.java
 │    └─ ForgetPassword.java
 ├─ employee/
 │    ├─ AddEmployee.java
 │    ├─ AddProduct.java
 │    ├─ EmployeeController.java
 │    ├─ EmployeeManger.java
 │    ├─ FindPasswordEmployee.java
 │    ├─ ForgetPasswordEmployee.java
 │    └─ ProductList.java
 └─ Porder/
      ├─ AddPorder.java
      ├─ FindAllPorderCustomer.java
      └─ PorderMangerCustomer.java

dao
 ├─ CustomerDao.java
 ├─ EmployeeDao.java
 ├─ PorderDao.java
 ├─ ProductDao.java
 └─ impl/
      ├─ CustomerDaoImpl.java
      ├─ EmployeeDaoImpl.java
      ├─ PorderDaoImpl.java
      └─ ProductDaoImpl.java

model
 ├─ Customer.java
 ├─ Employee.java
 ├─ Porder.java
 └─ Product.java

service
 ├─ CustomerService.java
 ├─ EmployeeService.java
 └─ impl/
      ├─ CustomerServiceImpl.java
      └─ EmployeeServiceImpl.java

util
 ├─ DbConnection.java
 ├─ ExcelExporter.java
 └─ Tool.java
```


 <br>

 功能特色

- **會員管理**：新增會員、找回密碼  
- **員工管理**：新增員工、產品維護  
- **產品管理**：新增、刪除、查詢產品  
- **訂單管理**：新增訂單、查詢客戶訂單  
- **資料存取**：使用 DAO + MySQL  
- **匯出功能**：支援將訂單資料匯出成 **Excel**
<br>
環境需求

- Java 8 以上  
- Maven 3.x  
- MySQL 5.7/8.0



<br>
預覽圖
<br>
<img width="432" height="289" alt="螢幕擷取畫面 2025-08-25 092744" src="https://github.com/user-attachments/assets/ee5fc516-1e04-4c5f-9252-15cf56e39638" />
<br>
<img width="430" height="287" alt="螢幕擷取畫面 2025-08-25 092814" src="https://github.com/user-attachments/assets/e6134542-0c5c-48a1-9bb8-b22d5024e98e" />
<br>
<img width="584" height="385" alt="螢幕擷取畫面 2025-08-25 092845" src="https://github.com/user-attachments/assets/e8037f50-1bf9-4c47-99f7-878d2b71a17e" />
<br>
<img width="399" height="204" alt="螢幕擷取畫面 2025-08-25 092917" src="https://github.com/user-attachments/assets/c4449fee-0654-4eac-8eeb-9fbd9fafc062" />
<br>
<img width="260" height="114" alt="螢幕擷取畫面 2025-08-25 093016" src="https://github.com/user-attachments/assets/5dfd0731-24ed-4768-8a3b-0749b6d81e8a" />
<br>
訂單管理
<br>
<img width="581" height="497" alt="螢幕擷取畫面 2025-08-25 093042" src="https://github.com/user-attachments/assets/f18f30eb-7763-43b4-89de-e1871799c35b" />
