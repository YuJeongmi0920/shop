package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OutIdDao {
    // 탈퇴 회원의 아이디를 입력
    // CustomerService.removeCustomer(Customer paramCustomer)가 호출
    public int insertOutId(Connection conn, String Id) throws Exception {
        System.out.println("customerId = " + Id);
        int row = 0;
        PreparedStatement psmt = null;
        try {
            String sql = "INSERT INTO outid(out_id,out_date)VALUES(?,NOW())";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, Id);
            psmt.executeUpdate();
        } finally {
            if (psmt != null) {
                psmt.close();
            }
            return row;
        }

    }
}


