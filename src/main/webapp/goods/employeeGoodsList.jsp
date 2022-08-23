<%@ page import="java.util.List" %>
<%@ page import="dto.Employee" %>
<%@ page import="service.GoodsService" %>
<%@ page import="dto.Goods" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Employee employee = (Employee) session.getAttribute("employee");
    if (employee == null) {
        out.print("<script>alert('권한없음');location.href='/login/loginForm.jsp'</script>");
    }

    int rowPerPage = 10;
    int currentPage = 1;
    String current = request.getParameter("currentPage");
    if (current != null) {
        currentPage = Integer.parseInt(current);
    }
    GoodsService goodsService = new GoodsService();
    List<Goods> goodsList = goodsService.getGoodsListByPage(rowPerPage, currentPage);
    int total = goodsService.getGoodSTotal();
    // 페이지네이션 세팅
    int lastPage = total / rowPerPage;
    if (total % rowPerPage != 0) {
        lastPage += 1;
    }
    // 몇개 페이지 나타낼껀지
    int pageCount = 10;
    // 공식
    int startPage = ((currentPage - 1) / pageCount) * pageCount + 1;
    int endPage = (((currentPage - 1) / pageCount) + 1) * pageCount;
    if (lastPage < endPage) {
        endPage = lastPage;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script defer src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script defer src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <!-- Popper JS -->
    <script defer src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div><!-- 상단메뉴 -->
    <ul>
        <li><a href="<%=request.getContextPath()%>/goods/employeeList.jsp">사원관리</a></li>
        <li><a href="<%=request.getContextPath()%>/goods/employeeGoodsList.jsp">상품관리</a></li>
        <!-- 상품목록/등록/수정(품절)/삭제(장바구니,주문이 없는 경우) -->
        <li><a href="<%=request.getContextPath()%>/adminList/employeeOrdersList.jsp">주문관리</a></li><!-- 주문목록/수정 -->
        <li><a href="<%=request.getContextPath()%>/adminList/employeeCustomerList.jsp">고객관리</a></li>
        <!-- 고객목록/강제탈퇴/비밀번호수정(전달구현X) -->
        <li><a href="<%=request.getContextPath()%>/adminList/employeeNoticeList.jsp">공지관리</a></li><!-- 공지 CRUD -->
    </ul>
</div>

<div class="container-fluid">
    <h1>상품관리</h1>
    <table class="table table-hover table-striped">
        <thead>
        <tr>
            <th class="col-1">상품번호</th>
            <th class="col-1">상품이름</th>
            <th class="col-2">상품가격</th>
            <th class="col-2">수정날짜</th>
            <th class="col-2">생성날짜</th>
            <th class="col-2">상품상태</th>
            <th class="col-2">상품이미지</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Goods goods : goodsList) {
        %>
        <tr style="cursor: pointer" onclick="location.href='goodsDetail.jsp?goodsNo=<%=goods.getGoodsNo()%>'">
            <th><%=goods.getGoodsNo()%>
            </th>
            <td><%=goods.getGoodsName()%>
            </td>
            <td><%=goods.getGoodsPrice()%>
            </td>
            <td><%=goods.getUpdateDate()%>
            </td>
            <td><%=goods.getCreateDate()%>
            </td>
            <td><%=goods.getSoldOut()%>
            </td>
            <td><img style="width: 150px;height: 150px" src="<%=goods.getFileName()%>"/>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
        <div class="d-flex flex-row-reverse">
            <a href="/goods/goodsInsertForm.jsp" class="btn btn-primary" >상품등록</a>
        </div>
    </table>
    <%-- 페이지네이션 --%>
    <ul class="pagination justify-content-center">
        <%-- 이전 --%>
        <%
            if (startPage != 1) {
        %>
        <li class="page-item">
            <a class="page-link"
               href="/goods/employeeGoodsList.jsp?currentPage=<%=startPage-1%>">
                이전 </a>
        </li>
        <%
            }
        %>
        <%-- 페이지넘버 --%>
        <%
            for (int i = startPage; i <= endPage; i++) {
                if (currentPage == i) {
        %>
        <li class="page-item active">
            <a class="page-link"><%=i%>
            </a>
        </li>
        <%
        } else {
        %>
        <li class="page-item">
            <a class="page-link"
               href="/goods/employeeGoodsList.jsp?currentPage=<%=i%>"><%=i%>
            </a>
        </li>
        <%
                }
            }
        %>
        <%-- 다음버튼 --%>
        <%
            if (endPage != lastPage) {
        %>
        <li class="page-item">
            <a class="page-link"
               href="/goods/employeeGoodsList.jsp?currentPage=<%=endPage+1%>">다음</a>
        </li>
        <%
            }
        %>
    </ul>
</div>

</body>
</html>
