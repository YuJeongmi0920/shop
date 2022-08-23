<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="service.GoodsService" %>
<%@ page isELIgnored="false" %>
<%
    // Controller : java class <- Serlvet
    int rowPerPage = 10;
    int currentPage = 1;
    if (request.getParameter("currentPage") != null) {
        currentPage = Integer.parseInt(request.getParameter("currentPage"));
    }
    GoodsService goodsService = new GoodsService();
    String check = request.getParameter("check");
    // list
    List<Map<String, Object>> list = goodsService.getCustomerGoodsListByPage(rowPerPage, currentPage, check);
    int total = goodsService.getGoodsListTotal();
    // 페이지네이션 세팅
    int lastPage = total / rowPerPage;
    if (total % rowPerPage != 0) {
        lastPage += 1;
    }
    // 몇개 페이지 나타낼껀지
    int pageCount = 5;
    // 공식
    int startPage = ((currentPage - 1) / pageCount) * pageCount + 1;
    int endPage = (((currentPage - 1) / pageCount) + 1) * pageCount;
    if (lastPage < endPage) {
        endPage = lastPage;
    }
%>

<!-- 분리하면 servlet / 연결기술 forword(request, resopnse) / jsp -->

<!-- View : 태그 -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link href="/resource/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script src="/resource/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<c:set var="list" value="<%=list%>"/>
<div class="container px-4 px-lg-5 mt-5">
    <div>
        <a href="./customerGoodsList.jsp">인기순</a>
        <a href="./customerGoodsList.jsp?check=row">낮은가격 수</a>
        <a href="./customerGoodsList.jsp?check=high">높은가격 수</a>
        <a href="./customerGoodsList.jsp?check=date">최신순</a>
    </div>
    <div class="row">
        <c:forEach items="${list}" var="m" varStatus="status">
            <div class="col-3 my-3">
                <div class="card h-100" style="cursor: pointer" onclick="location.href='/goods/goodsDetail.jsp?goodsNo=${m.goodsNo}'">
                    <img src="${m.filename}" class="card-img-top" onerror="this.src='/resource/img/images.png'"
                         style="height: 200px">
                    <div class="card-body">
                        <hr/>
                        <h5 class="card-title">${m.goodsName}</h5>
                        <div class="card-text">가격 : ${m.goodsPrice}</div>
                        <p class="card-text">구매건수 : ${m.sumNum}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <%-- 페이지네이션 --%>
    <c:set var="startPage" value="<%=startPage%>"/>
    <c:set var="endPage" value="<%=endPage%>"/>
    <c:set var="currentPage" value="<%=currentPage%>"/>
    <c:set var="lastPage" value="<%=lastPage%>"/>
    <ul class="pagination justify-content-center my-2 mb-2">
        <%-- 이전 --%>
        <c:if test="${startPage ne 1}">
            <li class="page-item">
                <a class="page-link"
                   href="customerGoodsList.jsp?currentPage=${startPage-1}">
                    이전 </a>
            </li>
        </c:if>
        <%-- 페이지넘버 --%>
        <c:forEach begin="${startPage}" end="${endPage}" varStatus="status">
            <c:if test="${currentPage eq status.index}">
                <li class="page-item active">
                    <a class="page-link">${status.index}
                    </a>
                </li>
            </c:if>
            <c:if test="${currentPage ne status.index}">
                <li class="page-item">
                    <a class="page-link"
                       href="customerGoodsList.jsp?currentPage=${status.index}">${status.index}
                    </a>
                </li>
            </c:if>
        </c:forEach>
        <%-- 다음버튼 --%>
        <c:if test="${endPage ne lastPage}">
            <li class="page-item">
                <a class="page-link"
                   href="customerGoodsList.jsp?currentPage=${endPage+1}">다음</a>
            </li>
        </c:if>
    </ul>
</div>
</body>
</html>
