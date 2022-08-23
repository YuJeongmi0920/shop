
<%@ page import="java.io.File" %>
<%@ page import="dto.Goods" %>
<%@ page import="dto.GoodsImg" %>
<%@ page import="service.GoodsService" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    int max = 1024 * 1024 * 15; // 15MB로 제한
    String dir = request.getServletContext().getRealPath("/upload");
    File file = new File(dir);
    if (!file.exists()) {
        file.mkdir();
    }
    MultipartRequest mRequest = new MultipartRequest(request, dir, max, "utf-8", new DefaultFileRenamePolicy());


    String goodsName = mRequest.getParameter("goodsName");
    int goodsPrice = Integer.parseInt(mRequest.getParameter("goodsPrice"));
    String soldOut = mRequest.getParameter("soldOut");

    // 파일 마임타입(Mime type)
    String contentType = mRequest.getContentType("file");
    // 원본파일 이름
    String originFileName = mRequest.getOriginalFileName("file");
    // 새로운 파일 이름
    String filename = mRequest.getFilesystemName("file");

    Goods goods = new Goods();
    goods.setGoodsName(goodsName);
    goods.setGoodsPrice(goodsPrice);
    goods.setSoldOut(soldOut);
    GoodsImg goodsImg = new GoodsImg();
    goodsImg.setFileName(filename);
    goodsImg.setContentType(contentType);
    goodsImg.setOriginFileName(originFileName);
    GoodsService goodsService = new GoodsService();
    if (goodsService.addGoods(goods, goodsImg)) {
        String url = "/goods/employeeGoodsList.jsp";
        out.print("<script>alert('등록성공');location.href='" + url + "'</script>");
        return;
    }
    String url = "/goods/employeeGoodsList.jsp";
    out.print("<script>alert('등록실패');location.href='" + url + "'</script>");
%>