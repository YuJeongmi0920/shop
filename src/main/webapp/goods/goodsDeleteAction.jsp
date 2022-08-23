<%@ page import="dto.Employee" %>
<%@ page import="java.io.File" %>
<%@ page import="service.GoodsService" %>
<%@ page import="dto.Goods" %>
<%@ page import="dto.GoodsImg" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    Employee employee = (Employee) session.getAttribute("employee");
    if (employee == null) {
        out.print("<script>alert('권한없음');location.href='login/loginForm.jsp'</script>");
    }


    int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
    String filename = request.getParameter("filename");

    System.out.println("goodsNo = " + goodsNo);
    System.out.println("filename = " + filename);


    GoodsService goodsService = new GoodsService();


    if (goodsService.dropGoods(goodsNo)) {
        String dir = request.getServletContext().getRealPath(filename);
        File file = new File(dir);
        if (file.exists()) {
            file.delete();
        }
        String url = "/goods/employeeGoodsList.jsp";
        out.print("<script>alert('삭제성공');location.href='" + url + "'</script>");
        return;
    }

    String url = "/goods/goodsDetail.jsp?goodsNo=" + goodsNo;
    out.print("<script>alert('삭제실패');location.href='" + url + "'</script>");


%>

