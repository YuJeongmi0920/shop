
<%@ page import="java.util.Map" %>

<%@ page import="dto.Employee" %>
<%@ page import="service.OrderService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Employee employee = (Employee) session.getAttribute("employee");
    if (employee == null) {
        out.print("<script>alert('권한없음');location.href='login/loginForm.jsp'</script>");
    }

    int orderNo = Integer.parseInt(request.getParameter("orderNo"));
    System.out.println("orderNo = " + orderNo);
    OrderService orderService = new OrderService();
    Map<String, Object> orderOne = orderService.getOrderOne(orderNo);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">
    <form action="/goods/goodsModifyAction.jsp" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <h2 class="form-label">상품이미지</h2>
            <img style="width: 150px; height: 150px" src="<%=orderOne.get("filename")%>">
            <hr/>
        </div>
        <div class="mb-3">
            <h2 class="form-label">상품명</h2>
            <input class="form-control" type="text" name="goodsName" value="<%=orderOne.get("goodsName")%>">
            <hr/>
        </div>
        <div class="mb-3">
            <h2 class="form-label">가격</h2>
            <input class="form-control" type="text" name="goodsPrice" value="<%=orderOne.get("goodsPrice")%>">
            <hr/>
        </div>
        <div class="mb-3">
            <h2 class="form-label">고객아이디</h2>
            <%=orderOne.get("customerId")%>
            <hr/>
        </div>
        <div class="mb-3">
            <h2 class="form-label">고객이름</h2>
            <%=orderOne.get("customerName")%>
            <hr/>
        </div>
        <div class="mb-3">
            <h2 class="form-label">고객주소</h2>
            <%=orderOne.get("customerAddress")%>
            <hr/>
        </div>
        <div class="mb-3">
            <h2 class="form-label">주문횟수</h2>
            <%=orderOne.get("orderQuantity")%>
            <hr/>
        </div>
        <div class="mb-3">
            <h2 class="form-label">주문상태</h2>
            <%=orderOne.get("orderState")%>
            <hr/>
        </div>
        <div class="mb-3">
            <h2 class="form-label">등록날짜</h2>
            <%=orderOne.get("createDate")%>
            <hr/>
        </div>
        <input type="submit" class="btn btn-primary" value="수정">
        <a class="btn btn-primary">삭제</a>
        <a class="btn btn-primary" href="/order/orderList.jsp">돌아가기</a>
    </form>
</div>
</body>
</html>
