package service;

import dao.NoticeDao;
import dto.Notice;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class NoticeService {

    public List<Notice> getNoticeList(int rowPerPage, int beginRow) {
        Connection conn = null;
        List<Notice> list = null;
        try {
            conn = DBUtil.getConnection();
            // executeUpdate() 실행 시 자동 커밋을 막음

            NoticeDao noticeDao = new NoticeDao();
            list = noticeDao.getNoticeList(conn, rowPerPage, beginRow);

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


    public int getNoticeListTotal() {
        Connection conn = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            // executeUpdate() 실행 시 자동 커밋을 막음
            result = new NoticeDao().getNoticeListTotal(conn);
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


    public int addNoticeList(Notice notice) {
        System.out.println(notice);
        int num = 0;
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            NoticeDao noticeDao = new NoticeDao();
            num = noticeDao.insertNotice(conn, notice);
            System.out.println("여기도 옴 ? " + num);
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return num;
    }


    public Notice getNoticeReadDetail(int noticeNo) {
        System.out.println("----------------여기 오나 찍히는거 확인" +noticeNo);
        Notice notice = null;
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            NoticeDao noticeDao = new NoticeDao();

            int check = noticeDao.readUpdate(conn,noticeNo);
            if (check != 0) {
                notice = noticeDao.selectRead(conn,noticeNo);
            }
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(notice);
        }
        return notice;

    }
}


