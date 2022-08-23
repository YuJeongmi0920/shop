<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
<div class="d-flex justify-content-center container" style="height: 450px">
    <form id="customerForm" method="post" action="/customer/customerLoginAction.jsp"
          class="w-75 border p-3 bg-white shadow rounded align-self-center">
        <div class="d-inline-flex">
            <h1 class="ms-2 fw-bold">쇼핑몰 고객 로그인</h1>
        </div>
        <div class="mb-3">
            <label for="customerId" class="form-label">아이디</label>
            <input type="text" class="form-control" name="customerId" id="customerId" placeholder="아이디를 입력해주세요">
        </div>
        <div class="mb-3">
            <label for="customerPass" class="form-label">비밀번호</label>
            <input type="password" class="form-control" name="customerPass" id="customerPass"
                   placeholder="비밀번호를 입력해주세요">
        </div>
        <div class="text-center mt-3">
            <button type="button" id="customerBtn" class="btn btn-danger rounded-0 me-1">고객 로그인</button>
            <button class="btn btn-danger rounded-0" type="button"
                    onclick="location.href = '../customer/addCustomer.jsp'">고객 회원가입
            </button>
        </div>
        <c:set var="request" value="<%=request%>"/>
        <c:if test="${request.getParameter('errorMsg') ne null}">
            ${request.getParameter('errorMsg')}
        </c:if>
    </form>
</div>
<div class="d-flex justify-content-center container">
    <form id="employeeForm" method="post" action="/employee/employeeLoginAction.jsp"
          class="w-75 border p-3 bg-white shadow rounded align-self-center">
        <div class="d-inline-flex">
            <h1 class="ms-2 fw-bold">쇼핑몰 스텝 로그인</h1>
        </div>
        <div class="mb-3">
            <label for="employeeId" class="form-label">아이디</label>
            <td><input type="text" class="form-control" name="employeeId" id="employeeId" placeholder="아이디를 입력해주세요">
            </td>
        </div>
        <div class="mb-3">
            <label for="employeePass" class="form-label">비밀번호</label>
            <td><input type="password" class="form-control" name="employeePass" id="employeePass"
                       placeholder="비밀번호를 입력해주세요"></td>
        </div>
        <div class="text-center mt-3">
            <button type="button" id="employeeBtn" class="btn btn-danger rounded-0 me-1">스텝 로그인</button>
            <button class="btn btn-danger rounded-0" type="button"
                    onclick="location.href = '../employee/addEmployee.jsp'">관리자 회원가입
            </button>
        </div>
        <c:if test="${request.getParameter('errorMsg2') ne null}">
            ${request.getParameter('errorMsg2')}
        </c:if>
    </form>
</div>

</body>
<script>
    $('#customerBtn').click(function () {
        if ($('#customerId').val() == '') {
            alert('고객 아이디를 입력하세요');
        } else if ($('#customerPass').val() == '') {
            alert('고객 패스워드를 입력하세요');
        } else {
            $('#customerForm').submit();
        }
    });

    $('#employeeBtn').click(function () {
        if ($('#employeeId').val() == '') {
            alert('스텝 아이디를 입력하세요');
        } else if ($('#employeePass').val() == '') {
            alert('스텝 패스워드를 입력하세요');
        } else {
            $('#employeeForm').submit();
        }
    });
</script>
</html>
