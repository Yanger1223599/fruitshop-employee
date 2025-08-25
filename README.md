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

