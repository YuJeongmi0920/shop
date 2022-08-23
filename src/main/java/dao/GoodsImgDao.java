package dao;


import dto.GoodsImg;
import java.sql.*;

public class GoodsImgDao {

    public int insertGoodsImg(Connection conn, GoodsImg goodsImg) throws SQLException {
        int result = 0;
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO goods_img(goods_no,filename,origin_filename,content_type,create_date) " +
                "VALUES (?,?,?,?,NOW())");
        stmt.setInt(1, goodsImg.getGoodsNo());
        stmt.setString(2, "/upload/" + goodsImg.getFileName());
        stmt.setString(3, goodsImg.getOriginFileName());
        stmt.setString(4, goodsImg.getContentType());
        // 1) insert
        // 2) select last_ai_key from...
        result = stmt.executeUpdate(); // insert 성공한 row 수

        if (stmt != null) {
            stmt.close();
        }
        return result;
    }

    public int updateGoodsImg(Connection conn, GoodsImg goodsImg) throws Exception {
        PreparedStatement stmt = conn.prepareStatement("UPDATE goods_img SET filename= ? ,origin_filename= ? ,content_type = ? WHERE goods_no= ?");
        stmt.setString(1, "/upload/" + goodsImg.getFileName());
        stmt.setString(2, goodsImg.getOriginFileName());
        stmt.setString(3, goodsImg.getContentType());
        stmt.setInt(4, goodsImg.getGoodsNo());
        int result = stmt.executeUpdate();
        if (stmt != null) {
            stmt.close();
        }
        return result;
    }

    public int deleteGoodsImg(Connection conn, int goodsNo) throws Exception {
        int num = 0;
        PreparedStatement stmt = null;
        try {
            String sql = "DELETE FROM goods_img WHERE goods_no=?";
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
