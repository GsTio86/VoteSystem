# 線上投票系統

**VoteSystem** 是一個基於 **Spring Boot** 和 **Vue.js** 的線上投票系統，專注於 **投票功能實現**。  
系統採用前後端分離的架構，提升了模組化與擴展性，方便進行擴充與維護。  
此專案可用於投票項目管理、即時統計票數和使用者投票互動。

---

## **專案功能**

### **後台管理功能**
- **投票項目管理**：支援新增、修改和刪除投票項目。
- **票數統計**：顯示每個投票項目的票數。

### **使用者功能**
- **查看投票項目**：顯示所有投票項目及其投票數。
- **進行投票**：支援使用者多選投票。

### **實時更新功能**
- 投票數據每隔幾秒自動刷新，確保展示最新票數。

---

## **使用的技術與庫**

- **[Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)**：專案使用的 Java 版本。
- **[Spring Boot](https://spring.io/projects/spring-boot)**：作為後端主要框架。
- **[Vue.js](https://vuejs.org/)**：作為前端框架，用於構建互動式用戶界面。
- **[Element+](https://element-plus.org/)**：第三方UI庫，用於設計整體UI。
- **[Axios](https://axios-http.com/)**：用於API串接。
- **[MySQL](https://www.mysql.com/)**：使用資料庫管理基本的CRUD。
- **[Lombok](https://projectlombok.org/)**：簡化 Java 程式的開發。
- **[Swagger UI](https://swagger.io/tools/swagger-ui/)**：提供 API 文檔和測試工具。

### **克隆專案**
* 克隆此專案後端部分：
```
git clone https://github.com/GsTio86/VoteSystem -b backend
```
* 克隆此專案前端部分：
```
git clone https://github.com/GsTio86/VoteSystem -b frontend
```
