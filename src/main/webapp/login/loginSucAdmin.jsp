
<%@ page import="dto.Employee" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    Employee employee = (Employee) session.getAttribute("employee");
    if (employee == null) {
        out.print("<script>alert('권한없음');location.href='/login/loginForm.jsp'</script>");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>

<br>
<%=employee.getEmployeeId()%>
님 환영합니다!
<br>
관리자 이름:
<%=employee.getEmployeeName()%>
<a href="/employee/removeEmployeeForm.jsp">관리자탈퇴</a>
<a href="/login/logout.jsp">로그아웃</a>
<a href="/adminList/customer-list">고객목록</a>
<a href="/notice/notice-list">공지사항</a>
<a href="/employeeList/employeeList.jsp">사원목록</a>
<a href="/goods/employeeGoodsList.jsp">상품목록</a>
<a href="/order/orderList.jsp">주문내역</a>
</body>
</html>