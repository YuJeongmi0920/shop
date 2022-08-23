<%@ page import="dto.Customer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div class="col-sm-10">
        <%
    Customer customer = (Customer) session.getAttribute("customer");
%>님 환영합니다!
    <br>
        <%=customer.getCustomerId()%> <!-- 고객 로그인 아이디 -->
    <br>
        <%=customer.getCustomerName()%> <!-- 고객 로그인 이름 -->
    <a href="/adminList/customer-detail" class="btn btn-primary mx-2 my-2">주문목록</a>
    <a href="/notice/notice-list" class="btn btn-primary mx-2 my-2">공지사항</a>
    <a href="/goods/customerGoodsList.jsp" class="btn btn-primary mx-2 my-2">상품정보</a>
    <a href="/customer/removeCustomerForm.jsp" class="btn btn-primary mx-2 my-2">회원탈퇴</a>
    <a href="/login/logout.jsp" class="btn btn-primary mx-2 my-2">로그아웃</a>
</body>
</html>
