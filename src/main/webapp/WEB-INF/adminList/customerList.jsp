<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<div class="container-fluid">
    <table class="table table-hover table-striped">
        <thead>
        <tr>
            <th class="col-4">고객아이디</th>
            <th class="col-4">고객이름</th>
            <th class="col-4">고객주소</th>
            <th class="col-4">고객전화번호</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="list">
            <tr style="cursor: pointer"
                onclick="location.href='/adminList/customer-detail?customerId=${list.customerId}'">
                <th>
                        ${list.customerId}
                </th>
                <td>
                        ${list.customerName}
                </td>
                <td>
                        ${list.customerAddress}
                </td>
                <td>
                        ${list.customerTelephone}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%-- 페이지네이션 --%>
    <ul class="pagination justify-content-center my-2 mb-2">
        <%-- 이전 --%>
        <c:if test="${pageNation.startPage ne 1}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageNation.path}?currentPage=${pageNation.startPage-1}">
                    이전 </a>
            </li>
        </c:if>
        <%-- 페이지넘버 --%>
        <c:forEach begin="${pageNation.startPage}" end="${pageNation.endPage}" varStatus="status">
            <c:if test="${pageNation.currentPage eq status.index}">
                <li class="page-item active">
                    <a class="page-link">${status.index}
                    </a>
                </li>
            </c:if>
            <c:if test="${pageNation.currentPage ne status.index}">
                <li class="page-item">
                    <a class="page-link"
                       href="${pageNation.path}?currentPage=${status.index}">${status.index}
                    </a>
                </li>
            </c:if>
        </c:forEach>
        <%-- 다음버튼 --%>
        <c:if test="${pageNation.endPage ne pageNation.lastPage}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageNation.path}?currentPage=${pageNation.endPage+1}">다음</a>
            </li>
        </c:if>
    </ul>
</div>
</body>
</html>
