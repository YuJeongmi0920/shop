package service;


import dao.CustomerDao;
import dao.OutIdDao;
import dto.Customer;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerService implements ICustomerService {
    public boolean removeCustomer(Customer paramCustomer) {

        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false); // executeUpdate()실행시 자동 커밋을 막음

            CustomerDao customerDao = new CustomerDao();
            customerDao.deleteCustomer(conn, paramCustomer);

            OutIdDao OutIdDao = new OutIdDao();
            OutIdDao.insertOutId(conn, paramCustomer.getCustomerId());

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace(); // console에 예외메세지 출력
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false; // 탈퇴 실패
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true; // 탈퇴 성공
    }


    public Customer getCustomerByIdAndPw(Customer paramCustomer) {
        Connection conn = null;
        Customer customer = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            // executeUpdate() 실행 시 자동 커밋을 막음

            CustomerDao customerDao = new CustomerDao();
            customer = customerDao.selectCustomerByIdAndPw(conn, paramCustomer);
            if (customer == null) { // 쿼리문이 정상적으로 적용되었는지 확인 후 아닐 시 예외처리
                throw new Exception();
            }

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace(); // console에 예외메세지 출력
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customer;
    }

    @Override
    public List<Customer> getCustomerList(int rowPerPage, int beginRow) {
        Connection conn = null;
        List<Customer> list = null;
        try {
            conn = DBUtil.getConnection();
            // executeUpdate() 실행 시 자동 커밋을 막음

            CustomerDao customerDao = new CustomerDao();
            list = customerDao.getCustomerList(conn, rowPerPage, beginRow);

        } catch (Exception e) {
            e.printStackTrace(); // console에 예외메세지 출력
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public int getCustomerListTotal() {
        Connection conn = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            // executeUpdate() 실행 시 자동 커밋을 막음
            result = new CustomerDao().getCustomerListTotal(conn);
        } catch (Exception e) {
            e.printStackTrace(); // console에 예외메세지 출력
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean idCheck(String id) {
        Connection conn = null;
        boolean check = true;
        try {
            conn = DBUtil.getConnection();
            // executeUpdate() 실행 시 자동 커밋을 막음
            CustomerDao customerDao = new CustomerDao();
            String result = customerDao.idCheck(conn, id);
            if (result != null) {
                check = false;
            }
        } catch (Exception e) {
            e.printStackTrace(); // console에 예외메세지 출력
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





