<%@ page import="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="service.GoodsService" %>
<%@ page import="dto.Employee" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%

    int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
    System.out.println("goodsNo = " + goodsNo);
    GoodsService goodsService = new GoodsService();
    Map<String, Object> goodsAndImgOne = goodsService.getGoodsAndImgOne(goodsNo);
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
    <form
            <c:if test="${sessionScope.get('employee') ne null}">action="/goods/goodsModifyAction.jsp"</c:if>
            <c:if test="${sessionScope.get('employee') eq null}">action="/customer-buy"</c:if>
            method="post">
        <input type="hidden" name="goodsNo" value="<%=goodsAndImgOne.get("goodsNo")%>">
        <div class="mb-3">
            <h2 class="form-label">상품이미지</h2>
            <img style="width: 150px; height: 150px" src="<%=goodsAndImgOne.get("filename")%>"
                 onerror="this.src = '/resource/img/images.png'">
            <hr/>
        </div>
        <div class="mb-3">
            <h2 class="form-label">상품번호</h2>
            <%=goodsAndImgOne.get("goodsNo")%>
            <input type="hidden" name="goodsNo" value="<%=goodsAndImgOne.get("goodsNo")%>">
            <hr/>
        </div>
        <c:if test="${sessionScope.get('employee') ne null}">
            <div class="mb-3">
                <h2 class="form-label">상품명</h2>
                <input type="text" name="goodsName" value="<%=goodsAndImgOne.get("goodsName")%>">
                <hr/>
            </div>
            <div class="mb-3">
                <h2 class="form-label">가격</h2>
                <input type="text" name="goodsPrice" value="<%=goodsAndImgOne.get("goodsPrice")%>">
                <hr/>
            </div>
        </c:if>
        <c:if test="${sessionScope.get('employee') eq null}">
            <div class="mb-3">
                <h2 class="form-label">상품명</h2>
                <%=goodsAndImgOne.get("goodsName")%>
                <hr/>
            </div>
            <div class="mb-3">
                <h2 class="form-label">가격</h2>
                <%=goodsAndImgOne.get("goodsPrice")%>
                <hr/>
            </div>
            <div class="mb-3">
                <h2 class="form-label">개수</h2>
                <select class="form-control" name="goodsCount">
                    <option selected>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                    <option>6</option>
                    <option>7</option>
                </select>
                <hr/>
            </div>
        </c:if>
        <c:if test="${sessionScope.get('employee') ne null}">
            <div class="mb-3">
                <h2 class="form-label">판매여부</h2>
                <select name="soldOut">
                    <%
                        if (goodsAndImgOne.get("soldOut").equals("N")) {
                    %>
                    <option selected>
                        N
                    </option>
                    <option>
                        Y
                    </option>
                    <%
                    } else {
                    %>
                    <option>
                        N
                    </option>
                    <option selected>
                        Y
                    </option>
                    <%
                        }
                    %>
                </select>
                <hr/>
            </div>
        </c:if>
        <div class="mb-3">
            <h2 class="form-label">등록날짜</h2>
            <%=goodsAndImgOne.get("createDate")%>
            <hr/>
        </div>
        <c:if test="${sessionScope.get('employee') ne null}">
            <input type="submit" class="btn btn-primary" value="수정">
            <a class="btn btn-primary" href="/goods/employeeGoodsList.jsp">돌아가기</a>
        </c:if>
        <c:if test="${sessionScope.get('employee') eq null}">
            <input type="submit" class="btn btn-primary" value="구매하기">
            <a class="btn btn-primary" href="/goods/customerGoodsList.jsp">돌아가기</a>
        </c:if>
    </form>
</div>
</body>
</html>
