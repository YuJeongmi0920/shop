package dao;

import dto.Notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NoticeDao {

    public List<Notice> getNoticeList(Connection conn, int rowPerPage, int beginRow) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Notice> list = new ArrayList<>();
        try {
            String sql = "select notice_no,notice_title,notice_content,create_date,notice_read FROM notice ORDER BY notice_no DESC LIMIT ?,?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, beginRow);
            stmt.setInt(2, rowPerPage);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Notice notice = new Notice();
                notice.setNoticeNo(rs.getInt("notice_no"));
                notice.setNoticeTitle(rs.getString("notice_title"));
                notice.setNoticeContent(rs.getString("notice_content"));
                notice.setCreateDate(rs.getString("create_date"));
                notice.setNoticeRead(rs.getInt("notice_read"));

                list.add(notice);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return list;
    }

    public int getNoticeListTotal(Connection conn) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            String sql = "select count(*) FROM notice";
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


    public int insertNotice(Connection conn, Notice notice) throws Exception {
        System.out.println(notice);
        int num = 0;
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO notice(notice_title,notice_content,create_date) VALUES (?,?,NOW())";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, notice.getNoticeTitle());
            stmt.setString(2, notice.getNoticeContent());
            num = stmt.executeUpdate();
            System.out.println("성공했냐 ? " + num);
        } finally {
            stmt.close();
        }
        return num;
    }

    public int readUpdate(Connection conn, int noticeNo) throws Exception {

        int result = 0;
        PreparedStatement stmt = null;
        try {
            String sql = "UPDATE notice SET notice_read = notice_read + 1 WHERE notice_no= ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, noticeNo);
            result = stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            return result;
        }
    }

    public Notice selectRead(Connection conn, int noticeNo) throws Exception {
        Notice notice = new Notice();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT notice_no,notice_title,notice_content,create_date,notice_read FROM notice WHERE notice_no = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, noticeNo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                notice.setNoticeNo(rs.getInt("notice_no"));
                notice.setNoticeTitle(rs.getString("notice_title"));
                notice.setNoticeContent(rs.getString("notice_content"));
                notice.setCreateDate(rs.getString("create_date"));
                notice.setNoticeRead(rs.getInt("notice_read"));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return notice;
    }
}

