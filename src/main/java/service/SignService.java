package service;


import dao.SignDao;
import dto.Customer;
import dto.Employee;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class SignService {
    private SignDao signDao;

    public boolean idCheck(String id) {
        boolean result = false;
        this.signDao = new SignDao();
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String ckid = signDao.idCheck(conn, id);
            if (ckid == null) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean insertCustomer(Customer customer) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false); // executeUpdate()실행시 자동 커밋을 막음

            this.signDao = new SignDao();
            signDao.insertCustomer(conn, customer);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    public boolean insertEmployee(Employee employee) {
        Connection conn = null;
        boolean check = false;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false); // executeUpdate()실행시 자동 커밋을 막음

            this.signDao = new SignDao();
            check = signDao.insertEmployee(conn, employee);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return check;
    }
}
