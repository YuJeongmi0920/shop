package dao;


import dto.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersDao {
    // 5-2) 주문상세보기
    public Map<String, Object> selectOrdersOne(Connection conn, int ordersNo) throws Exception {
        Map<String, Object> map = new HashMap<>();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT o.order_no orderNo, o.create_date createDate, o.customer_id customerId, o.order_quantity orderQuantity, o.order_state orderState, c.customer_name customerName, c.customer_address customerAddress, c.customer_telephone customerTelephone, g.goods_name goodsName, g.goods_price goodsPrice, i.filename fileName FROM orders o JOIN goods g ON o.goods_no = g.goods_no JOIN customer c ON o.customer_id = c.customer_id JOIN goods_img i ON g.goods_no = i.goods_no WHERE order_no = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ordersNo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                map.put("orderNo", rs.getInt("orderNo"));
                map.put("customerId", rs.getString("customerId"));
                map.put("customerName", rs.getString("customerName"));
                map.put("customerAddress", rs.getString("customerAddress"));
                map.put("customerTelephone", rs.getString("customerTelephone"));
                map.put("orderQuantity", rs.getInt("orderQuantity"));
                map.put("orderState", rs.getString("orderState"));
                map.put("goodsName", rs.getString("goodsName"));
                map.put("goodsPrice", rs.getInt("goodsPrice"));
                map.put("createDate", rs.getString("createDate"));
                map.put("filename", rs.getString("filename"));
            }
            System.out.println(map);
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return map;
    }

    public List<Map<String, Object>> selectOrderList(Connection conn, int beginRow, int rowPerPage) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = new ArrayList<>();

        try {
            String sql = "SELECT o.order_no orderNo,o.create_date createDate, o.customer_id customerId,o.order_quantity orderQuantity,o.order_state orderState, c.customer_name customerName, c.customer_address customerAddress,c.customer_telephone customerTelephone, g.goods_name goodsName, g.goods_price goodsPrice FROM orders o JOIN goods g ON o.goods_no = g.goods_no JOIN customer c ON o.customer_id = c.customer_id order by orderNo desc LIMIT ?, ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, beginRow);
            stmt.setInt(2, rowPerPage);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("orderNo", rs.getInt("orderNo"));
                map.put("customerId", rs.getString("customerId"));
                map.put("customerName", rs.getString("customerName"));
                map.put("customerAddress", rs.getString("customerAddress"));
                map.put("customerTelephone", rs.getString("customerTelephone"));
                map.put("goodsName", rs.getString("goodsName"));
                map.put("goodsPrice", rs.getInt("goodsPrice"));
                map.put("orderQuantity", rs.getInt("orderQuantity"));
                map.put("orderState", rs.getString("orderState"));
                map.put("createDate", rs.getString("createDate"));
                list.add(map);
            }
            System.out.println(list);
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return list;
    }

    public int orderTotal(Connection conn) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            String sql = "select COUNT(*) from orders";
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


    //    // 2-1) 고객 한명의 주문 목록(관리자, 고객)
    public List<Map<String, Object>> selectOrdersListByCustomer(Connection conn, String customerId, int rowPerPage, int beginRow) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            String sql = "SELECT o.order_no orderNo,o.create_date createDate, o.customer_id customerId,o.order_quantity orderQuantity,o.order_state orderState, c.customer_name customerName, c.customer_address customerAddress,c.customer_telephone customerTelephone, g.goods_name goodsName, g.goods_price goodsPrice FROM orders o JOIN goods g ON o.goods_no = g.goods_no JOIN customer c ON o.customer_id = c.customer_id WHERE o.customer_id = ? order by orderNo desc LIMIT ?, ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, customerId);
            stmt.setInt(2, beginRow);
            stmt.setInt(3, rowPerPage);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("orderNo", rs.getInt("orderNo"));
                map.put("customerId", rs.getString("customerId"));
                map.put("customerName", rs.getString("customerName"));
                map.put("customerAddress", rs.getString("customerAddress"));
                map.put("customerTelephone", rs.getString("customerTelephone"));
                map.put("goodsName", rs.getString("goodsName"));
                map.put("goodsPrice", rs.getInt("goodsPrice"));
                map.put("orderQuantity", rs.getInt("orderQuantity"));
                map.put("orderState", rs.getString("orderState"));
                map.put("createDate", rs.getString("createDate"));
                list.add(map);
            }
            System.out.println(list);
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return list;
    }

    public int getCustomerOrderListTotal(Connection conn, String customerId) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            String sql = "SELECT COUNT(*) FROM orders o JOIN goods g ON o.goods_no = g.goods_no JOIN customer c ON o.customer_id = c.customer_id";
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

    public int insertOrder(Connection conn, String goodsNo, Customer customer, String goodsCount) throws Exception {
        PreparedStatement stmt = null;
        int result = 0;
        try {
            String sql = "INSERT INTO orders (goods_no,customer_id,order_quantity,order_state,update_date,create_date) VALUES (?,?,?,'N',NOW(),NOW())";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(goodsNo));
            stmt.setString(2, customer.getCustomerId());
            stmt.setInt(3, Integer.parseInt(goodsCount));
            result = stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
        }
        return result;
    }
}


