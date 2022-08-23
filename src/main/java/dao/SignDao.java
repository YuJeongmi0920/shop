package dao;


import dto.Customer;
import dto.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignDao {
    public String idCheck(Connection conn, String id) throws Exception {
        String ckId = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT t.id id FROM(SELECT customer_id id FROM customer UNION SELECT employee_id id FROM employee UNION SELECT out_id id FROM outid) t WHERE t.id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                ckId = rs.getString("id");
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return ckId;
    }

    //회원가입
    public Boolean insertCustomer(Connection conn, Customer customer) throws Exception {
        //return variable
        boolean result = false;
        PreparedStatement stmt = null;
        int row = 0;
        System.out.println("파라미터 : " + customer);
        try {
            String sql = "insert into Customer (customer_id, customer_pass, customer_name,customer_address,customer_telephone, update_date, create_date) value (?,password(?),?,?,?,now(),now())";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, customer.getCustomerId());
            stmt.setString(2, customer.getCustomerPass());
            stmt.setString(3, customer.getCustomerName());
            stmt.setString(4, customer.getCustomerAddress());
            stmt.setString(5, customer.getCustomerTelephone());

            row = stmt.executeUpdate();
        } finally {
            if (row == 1) {
                result = true;
            }
            if (stmt != null) stmt.close();
        }
        return result;
    }


    public Boolean insertEmployee(Connection conn, Employee employee) throws Exception {
        //return variable
        boolean result = false;
        PreparedStatement stmt = null;
        int row = 0;
        System.out.println("파라미터 : " + employee);
        try {
            String sql = "insert into employee (employee_id, employee_pass, employee_name, update_date, create_date) value (?,password(?),?,now(),now())";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, employee.getEmployeeId());
            stmt.setString(2, employee.getEmployeePass());
            stmt.setString(3, employee.getEmployeeName());
            row = stmt.executeUpdate();
        } finally {
            if (row == 1) {
                result = true;
            }
            if (stmt != null) stmt.close();
        }
        return result;
    }
}

