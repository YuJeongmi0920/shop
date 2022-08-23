package dao;

import dto.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsDao {
    // 고객 상품리스트 페이지로 반환
    public List<Map<String, Object>> customerGoodsListByPage(Connection conn, int beginRow, int rowPerPage, String check) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT g.goods_no goodsNo,g.goods_name goodsName,g.goods_price goodsPrice,gi.filename filename,COALESCE(t.sumNum, 0) sumNum FROM goods g LEFT JOIN (SELECT goods_no, COALESCE(SUM(order_quantity), 0) sumNum FROM orders GROUP BY goods_no) t ON g.goods_no = t.goods_no LEFT JOIN goods_img gi ON g.goods_no = gi.goods_no ORDER BY t.sumNUm DESC LIMIT ?,?";
            if (check != null) {
                switch (check) {
                    case "row":
                        sql = "SELECT g.goods_no goodsNo,g.goods_name goodsName,g.goods_price goodsPrice,gi.filename filename,COALESCE(t.sumNum, 0) sumNum FROM goods g LEFT JOIN (SELECT goods_no, COALESCE(SUM(order_quantity), 0) sumNum FROM orders GROUP BY goods_no) t ON g.goods_no = t.goods_no LEFT JOIN goods_img gi ON g.goods_no = gi.goods_no ORDER BY goodsPrice LIMIT ?,?";
                        break;
                    case "high":
                        sql = "SELECT g.goods_no goodsNo,g.goods_name goodsName,g.goods_price goodsPrice,gi.filename filename,COALESCE(t.sumNum, 0) sumNum FROM goods g LEFT JOIN (SELECT goods_no, COALESCE(SUM(order_quantity), 0) sumNum FROM orders GROUP BY goods_no) t ON g.goods_no = t.goods_no LEFT JOIN goods_img gi ON g.goods_no = gi.goods_no ORDER BY goodsPrice DESC LIMIT ?,?";
                        break;
                    case "date":
                        sql = "SELECT g.goods_no goodsNo,g.goods_name goodsName,g.goods_price goodsPrice,gi.filename filename,COALESCE(t.sumNum, 0) sumNum FROM goods g LEFT JOIN (SELECT goods_no, COALESCE(SUM(order_quantity), 0) sumNum FROM orders GROUP BY goods_no) t ON g.goods_no = t.goods_no LEFT JOIN goods_img gi ON g.goods_no = gi.goods_no ORDER BY g.create_date DESC LIMIT ?,?";
                        break;
                }
            }
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, beginRow);
            stmt.setInt(2, rowPerPage);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("goodsNo", rs.getInt("goodsNo"));
                map.put("goodsName", rs.getString("goodsName"));
                map.put("goodsPrice", rs.getInt("goodsPrice"));
                map.put("filename", rs.getString("filename"));
                map.put("sumNum", rs.getString("sumNum"));
                list.add(map);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return list;
    }

    public List<Goods> selectGoodsListByPage(Connection conn, int rowPerPage, int beginRow) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Goods> list = new ArrayList<>();

        try {
            String sql = "SELECT g.goods_no goodsNo,g.goods_name goodsName,g.goods_price goodsPrice,g.update_date updateDate,g.create_date createDate,g.sold_out soldOut,gi.filename FROM goods g JOIN goods_img gi ON g.goods_no = gi.goods_no ORDER BY g.goods_no DESC LIMIT ?,?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, rowPerPage);
            stmt.setInt(2, beginRow);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Goods goodsList = new Goods();
                goodsList.setGoodsNo(rs.getInt("goodsNo"));
                goodsList.setGoodsName(rs.getString("goodsName"));
                goodsList.setGoodsPrice(rs.getInt("goodsPrice"));
                goodsList.setUpdateDate(rs.getString("updateDate"));
                goodsList.setCreateDate(rs.getString("createDate"));
                goodsList.setSoldOut(rs.getString("soldOut"));
                goodsList.setFileName(rs.getString("fileName"));
                list.add(goodsList);

                System.out.println("goodsList = " + goodsList);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }

        return list;
    }

    public int getGoodTotal(Connection conn) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            String sql = "select COUNT(*) from goods";
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

    public Map<String, Object> selectGoodsAndImgOne(Connection conn, int goodsNo) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> map = null;
        try {
            String sql = "SELECT g.goods_no goodsNo,g.goods_name goodName,g.goods_price goodPrice,g.update_date updateDate,g.create_date createDate,g.sold_out soldOut,gi.filename FROM goods g LEFT JOIN goods_img gi ON g.goods_no = gi.goods_no WHERE g.goods_no = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, goodsNo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                map = new HashMap<>();
                map.put("goodsNo", rs.getInt("goodsNo"));
                map.put("goodsName", rs.getString("goodName"));
                map.put("goodsPrice", rs.getInt("goodPrice"));
                map.put("updateDate", rs.getString("updateDate"));
                map.put("createDate", rs.getString("createDate"));
                map.put("soldOut", rs.getString("soldOut"));
                map.put("filename", rs.getString("filename"));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return map;
    }
    /*

      쿼리에서 where 조건이 없다면..반환 타입 List<Map<String, Object>> list
      */


    // 반환값 : key값 (jdbc api)
    public int insertGoods(Connection conn, Goods goods) throws SQLException {
        int keyId = 0;
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO goods(goods_name,goods_price,update_date,create_date,sold_out)" +
                " VALUES (?,?,NOW(),NOW(),?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, goods.getGoodsName());
        stmt.setInt(2, goods.getGoodsPrice());
        stmt.setString(3, goods.getSoldOut());
        // 1) insert
        // 2) select last_ai_key from...
        stmt.executeUpdate(); // insert 성공한 row 수
        ResultSet rs = stmt.getGeneratedKeys(); // select last_key
        if (rs.next()) {
            keyId = rs.getInt(1);
        }

        if (stmt != null) {
            stmt.close();
        }
        if (rs != null) {
            rs.close();
        }
        return keyId;
    }

    public int updateGoods(Connection conn, Goods goods) throws Exception {
        PreparedStatement stmt = conn.prepareStatement("UPDATE goods SET goods_name= ? ,goods_price= ? ,sold_out = ?,update_date=NOW() WHERE goods_no= ?");
        // 1) insert
        // 2) select last_ai_key from...
        stmt.setString(1, goods.getGoodsName());
        stmt.setInt(2, goods.getGoodsPrice());
        stmt.setString(3, goods.getSoldOut());
        stmt.setInt(4, goods.getGoodsNo());
        int result = stmt.executeUpdate();// insert 성공한 row 수
        if (stmt != null) {
            stmt.close();
        }
        return result;
    }

    public int deleteGoods(Connection conn, int goodsNo) throws Exception {
        int num = 0;
        PreparedStatement stmt = null;
        try {
            String sql = "DELETE FROM goods WHERE goods_no =?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, goodsNo);
            num = stmt.executeUpdate();
            System.out.println(stmt);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            return num;
        }
    }
}

