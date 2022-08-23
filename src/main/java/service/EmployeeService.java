package service;




import dao.EmployeeDao;
import dao.OutIdDao;
import dto.Employee;
import util.DBUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    public boolean removeEmployee(Employee paramEmployee) {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false); // executeUpdate()실행시 자동 커밋을 막음

            EmployeeDao employeeDao = new EmployeeDao();
            employeeDao.deleteEmployee(conn, paramEmployee);

            OutIdDao OutIdDao = new OutIdDao();
            OutIdDao.insertOutId(conn, paramEmployee.getEmployeeId());

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


    public Employee getEmployeeByIdAndPw(Employee paramEmployee) {
        Connection conn = null;
        Employee employee = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            // executeUpdate() 실행 시 자동 커밋을 막음

            EmployeeDao employeeDao = new EmployeeDao();
            employee = employeeDao.selectEmployeeByIdAndPw(conn, paramEmployee);
            if (employee == null) { // 쿼리문이 정상적으로 적용되었는지 확인 후 아닐 시 예외처리
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
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employee;
    }

    public List<Employee> getEmployeeList(int rowPerPage, int currentPage) throws SQLException {
        Connection conn = null;
        List<Employee> employeeList = null;
        try {
            conn = DBUtil.getConnection();
            final int beginRow = (currentPage - 1) * rowPerPage;
            EmployeeDao employeeDao = new EmployeeDao();
            employeeList = employeeDao.selectEmployeeList(conn, rowPerPage, beginRow);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
        return employeeList;
    }

    public int getEmployeeTotal() throws SQLException {
        Connection conn = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            EmployeeDao employeeDao = new EmployeeDao();
            result = employeeDao.getEmployeeTotal(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
        return result;
    }

    public int modifyEmployeeActive(Employee employee) throws Exception {
        Connection conn = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            EmployeeDao employeeDao = new EmployeeDao();
            result = employeeDao.modifyEmployeeActive(conn, employee);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
        return result;
    }

}
