package dao;

import dto.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    public int deleteEmployee(Connection conn, Employee paramEmployee) throws Exception {
        // 동일한 conn
        // conn.close() X
        int row = 0;
        PreparedStatement psmt = null;
        try {
            String sql = "DELETE FROM employee WHERE employee_id = ? AND employee_pass= password(?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, paramEmployee.getEmployeeId());
            psmt.setString(2, paramEmployee.getEmployeePass());
            psmt.executeUpdate();
        } finally {
            if (psmt != null) psmt.close();
        }
        return row;

    }


    public Employee selectEmployeeByIdAndPw(Connection conn, Employee login) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Employee employeeLogin = new Employee();
        try {
            String sql = "select employee_id employeeId, employee_name employeeName from employee WHERE employee_id = ? AND employee_pass= password(?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, login.getEmployeeId());
            stmt.setString(2, login.getEmployeePass());
            rs = stmt.executeQuery();
            if (rs.next()) {
                employeeLogin.setEmployeeId(rs.getString("employeeId"));
                employeeLogin.setEmployeeName(rs.getString("employeeName"));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return employeeLogin;
    }

    public List<Employee> selectEmployeeList(Connection conn, int rowPerPage, int beginRow) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Employee> list = new ArrayList<>();
        try {
            String sql = "select employee_id employeeId, employee_name employeeName,create_date createDate,active from employee ORDER BY create_date DESC limit ?, ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, beginRow);
            stmt.setInt(2, rowPerPage);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Employee employeeLogin = new Employee();
                employeeLogin.setEmployeeId(rs.getString("employeeId"));
                employeeLogin.setEmployeeName(rs.getString("employeeName"));
                employeeLogin.setCreateDate(rs.getString("createDate"));
                employeeLogin.setActive(rs.getString("active"));
                list.add(employeeLogin);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return list;
    }

    public int getEmployeeTotal(Connection conn) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            String sql = "select COUNT(*) from employee";
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

    public int modifyEmployeeActive(Connection conn, Employee employee) throws Exception {
        PreparedStatement stmt = null;
        int result;
        try {
            String sql = "UPDATE employee SET active = ? WHERE employee_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, employee.getActive());
            stmt.setString(2, employee.getEmployeeId());
            result = stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
        }
        return result;
    }

}






