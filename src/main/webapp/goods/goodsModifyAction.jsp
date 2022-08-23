<%@ page import="java.io.File" %>
<%@ page import="dto.GoodsImg" %>
<%@ page import="service.GoodsService" %>
<%@ page import="dto.Goods" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    int max = 1024 * 1024 * 15; // 15MB로 제한
    String dir = request.getServletContext().getRealPath("/upload");
    File file = new File(dir);
    if (!file.exists()) {
        file.mkdir();
    }
    MultipartRequest mRequest = new MultipartRequest(request, dir, max, "utf-8", new DefaultFileRenamePolicy());

    int goodsNo = Integer.parseInt(mRequest.getParameter("goodsNo"));
    String goodsName = mRequest.getParameter("goodsName");
    int goodsPrice = Integer.parseInt(mRequest.getParameter("goodsPrice"));
    String preFileName = mRequest.getParameter("preFileName");
    String soldOut = mRequest.getParameter("soldOut");

    // 파일 마임타입(Mime type)
    String contentType = mRequest.getContentType("file");
    // 원본파일 이름
    String originFileName = mRequest.getOriginalFileName("file");
    // 새로운 파일 이름
    String filename = mRequest.getFilesystemName("file");


    // 이미지 세팅
    GoodsImg goodsImg = new GoodsImg();
    goodsImg.setGoodsNo(goodsNo);
    goodsImg.setFileName(filename);
    goodsImg.setContentType(contentType);
    goodsImg.setOriginFileName(originFileName);

    GoodsService goodsService = new GoodsService();
    // 상품 세팅
    Goods goods = new Goods();
    goods.setSoldOut(soldOut);
    goods.setGoodsNo(goodsNo);
    goods.setGoodsName(goodsName);
    goods.setGoodsPrice(goodsPrice);

    if (goodsService.modifyGoods(goodsImg, goods)) {
        if (filename != null) {
            File file1 = new File(preFileName);
            if (file1.exists()) {
                file1.delete();
            }
        }
        String url = "/goods/goodsDetail.jsp?goodsNo=" + goodsNo;
        out.print("<script>alert('수정성공');location.href='" + url + "'</script>");
        return;
    }
    String url = "/goods/goodsDetail.jsp?goodsNo=" + goodsNo;
    out.print("<script>alert('수정실패');location.href='" + url + "'</script>");
%>
