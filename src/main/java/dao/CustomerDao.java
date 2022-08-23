package dao;

import dto.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    // 탈퇴
    public int deleteCustomer(Connection conn, Customer paramCustomer) throws SQLException {
        // 동일한 conn
        // conn.close() X
        int row = 0;
        PreparedStatement psmt = null;
        try {
            String sql = "DELETE FROM customer WHERE customer_id = ? AND customer_pass= password(?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, paramCustomer.getCustomerId());
            psmt.setString(2, paramCustomer.getCustomerPass());
            psmt.executeUpdate();
        } finally {
            if (psmt != null) psmt.close();
        }
        return row;

    }


    //로그인

    public Customer selectCustomerByIdAndPw(Connection conn, Customer login) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Customer customerLogin = new Customer();
        try {
            String sql = "select customer_id customerId, customer_name customerName from customer WHERE customer_id= ? AND customer_pass= password(?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, login.getCustomerId());
            stmt.setString(2, login.getCustomerPass());
            rs = stmt.executeQuery();
            if (rs.next()) {
                customerLogin.setCustomerId(rs.getString("customerId"));
                customerLogin.setCustomerName(rs.getString("customerName"));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return customerLogin;
    }

    public List<Customer> getCustomerList(Connection conn, int rowPerPage, int beginRow) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Customer> list = new ArrayList<>();
        try {
            String sql = "select customer_id,customer_name,customer_address,customer_telephone,create_date FROM customer LIMIT ?,?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, beginRow);
            stmt.setInt(2, rowPerPage);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getString("customer_id"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setCustomerAddress(rs.getString("customer_address"));
                customer.setCustomerTelephone(rs.getString("customer_telephone"));
                customer.setCreateDate(rs.getString("create_date"));
                list.add(customer);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return list;
    }

    public int getCustomerListTotal(Connection conn) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            String sql = "select count(*) FROM customer";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return result;
    }

    public String idCheck(Connection conn, String id) throws Exception {
        //return variable
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String result = null;
        try {
            String sql = "SELECT customer_id FROM customer WHERE customer_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("customer_id");
            }
        } finally {
            if (stmt != null) stmt.close();
            if (rs != null) rs.close();
        }
        return result;
    }
}





