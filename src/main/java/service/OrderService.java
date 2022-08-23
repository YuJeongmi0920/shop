package service;

import dao.OrdersDao;
import dto.Customer;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderService implements IOrderService {
    private OrdersDao ordersDao;

    public Map<String, Object> getOrderOne(int orderNo) throws Exception {
        Connection conn = null;
        Map<String, Object> map = null;
        try {
            conn = new DBUtil().getConnection();
            ordersDao = new OrdersDao();
            map = ordersDao.selectOrdersOne(conn, orderNo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
        return map;
    }

    public List<Map<String, Object>> orderList(int rowPerPage, int currentPage) throws Exception {
        Connection conn = null;
        List<Map<String, Object>> orderList = null;
        try {
            conn = DBUtil.getConnection();
            final int beginRow = (currentPage - 1) * rowPerPage;
            ordersDao = new OrdersDao();
            orderList = ordersDao.selectOrderList(conn, beginRow, rowPerPage);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
        return orderList;
    }

    public int orderTotal() throws SQLException {
        Connection conn = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            ordersDao = new OrdersDao();
            result = ordersDao.orderTotal(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getCustomerOrderList(String customerId, int rowPerPage, int beginPage) {
        Connection conn = null;
        List<Map<String, Object>> list = null;
        try {
            conn = DBUtil.getConnection();
            ordersDao = new OrdersDao();
            list = ordersDao.selectOrdersListByCustomer(conn, customerId, rowPerPage, beginPage);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public int getCustomerOrderListTotal(String customerId) {
        Connection conn = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            ordersDao = new OrdersDao();
            result = ordersDao.getCustomerOrderListTotal(conn, customerId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public int insertOrder(String goodsNo, Customer customer, String goodsCount) {
        Connection conn = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            ordersDao = new OrdersDao();
            result = ordersDao.insertOrder(conn, goodsNo, customer, goodsCount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
