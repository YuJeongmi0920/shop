package service;


import dao.GoodsDao;
import dao.GoodsImgDao;
import dao.OrdersDao;
import dto.Goods;
import dto.GoodsImg;
import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GoodsService {
    private GoodsDao goodsDao;
    private GoodsImgDao goodsImgDao;


    public List<Goods> getGoodsListByPage(int rowPerPage, int currentPage) throws SQLException {
        Connection conn = null;
        this.goodsDao = new GoodsDao();
        List<Goods> goodsList = null;
        try {
            conn = DBUtil.getConnection();
            final int beginRow = (currentPage - 1) * rowPerPage;
            GoodsDao goodsDao = new GoodsDao();
            goodsList = goodsDao.selectGoodsListByPage(conn, beginRow, rowPerPage);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
        return goodsList;
    }

    public int getGoodSTotal() throws SQLException {
        Connection conn = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            GoodsDao goodsDao = new GoodsDao();
            result = goodsDao.getGoodTotal(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
        return result;
    }

    public Map<String, Object> getGoodsAndImgOne(int goodsNo) throws Exception {
        Connection conn = null;
        conn = new DBUtil().getConnection();
        conn.setAutoCommit(false);
        goodsDao = new GoodsDao();
        return goodsDao.selectGoodsAndImgOne(conn, goodsNo);
    }


    public boolean addGoods(Goods goods, GoodsImg goodsImg) {
        boolean result = true;
        Connection conn = null;
        try {
            conn = new DBUtil().getConnection();
            conn.setAutoCommit(false);
            goodsDao = new GoodsDao();
            goodsImgDao = new GoodsImgDao();
            int goodsNo = goodsDao.insertGoods(conn, goods); // goodsNo가 AI로 자동생성되어 DB입력
            if (goodsNo != 0) {
                goodsImg.setGoodsNo(goodsNo);
                if (goodsImgDao.insertGoodsImg(conn, goodsImg) == 0) {
                    throw new Exception(); // 이미지 입력실패시 강제로 롤백(catch절 이동)
                }
            }
            conn.commit();
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
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
        return result;
    }

    public boolean modifyGoods(GoodsImg goodsImg, Goods goods) {
        boolean result = true;
        Connection conn = null;
        try {
            conn = new DBUtil().getConnection();
            conn.setAutoCommit(false);
            goodsDao = new GoodsDao();
            goodsImgDao = new GoodsImgDao();
            int goodsNo = goodsDao.updateGoods(conn, goods); // goodsNo가 AI로 자동생성되어 DB입력
            if (goodsNo != 0 && goodsImg.getFileName() != null) {
                if (goodsImgDao.updateGoodsImg(conn, goodsImg) == 0) {
                    throw new Exception(); // 이미지 입력실패시 강제로 롤백(catch절 이동)
                }
            }
            conn.commit();
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
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
        return result;
    }


    public boolean dropGoods(int goodsNo) {
        boolean result = true;
        Connection conn = null;
        try {
            conn = new DBUtil().getConnection();
            conn.setAutoCommit(false);
            goodsDao = new GoodsDao();
            goodsImgDao = new GoodsImgDao();
            int check = goodsDao.deleteGoods(conn, goodsNo);
            if (check != 0) {
                if (goodsImgDao.deleteGoodsImg(conn, goodsNo) == 0) {
                    throw new Exception();
                }
            }
            conn.commit();
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
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
        return result;
    }

    public List<Map<String, Object>> getCustomerGoodsListByPage(int rowPerPage, int currentPage, String check) throws Exception {
        Connection conn = null;
        List<Map<String, Object>> customerGoodList = null;
        try {
            conn = DBUtil.getConnection();
            final int beginRow = (currentPage - 1) * rowPerPage;
            goodsDao = new GoodsDao();
            customerGoodList = goodsDao.customerGoodsListByPage(conn, beginRow, rowPerPage,check);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
        return customerGoodList;
    }


    public int getGoodsListTotal() throws SQLException {
        Connection conn = null;
        int result = 0;
        try {
            conn = DBUtil.getConnection();
            GoodsDao goodsDao = new GoodsDao();
            result = goodsDao.getGoodTotal(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }
        return result;
    }
}

