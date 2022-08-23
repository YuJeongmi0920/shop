<%@ page import="java.util.List" %>
<%@ page import="dto.Employee" %>
<%@ page import="service.EmployeeService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Employee employee = (Employee) session.getAttribute("employee");
    if (employee == null) {
        out.print("<script>alert('권한없음');location.href='login/loginForm.jsp'</script>");
    }
    int rowPerPage = 10;
    int currentPage = 1;
    String current = request.getParameter("currentPage");
    if (current != null) {
        currentPage = Integer.parseInt(current);
    }
    EmployeeService employeeService = new EmployeeService();
    List<Employee> employeeList = employeeService.getEmployeeList(rowPerPage, currentPage);
    int total = employeeService.getEmployeeTotal();
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
<div class="container-fluid">
    <table class="table table-hover table-striped">
        <thead>
        <tr>
            <th class="col-4">사원아이디</th>
            <th class="col-4">사원이름</th>
            <th class="col-4">생성날짜</th>
            <th class="col-4">정지여부</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Employee emp : employeeList) {
        %>
        <tr style="cursor: pointer">
            <th><%=emp.getEmployeeId()%>
            </th>
            <td><%=emp.getEmployeeName()%>
            </td>
            <td><%=emp.getCreateDate()%>
            </td>
            <td>
                <select class="active-value" emp-id="<%=emp.getEmployeeId()%>">
                    <%
                        if (emp.getActive().equals("N")) {
                    %>
                    <option>Y</option>
                    <option selected="selected">N</option>
                    <%
                    } else {
                    %>
                    <option selected="selected">Y</option>
                    <option>N</option>
                    <%
                        }
                    %>
                </select>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%-- 페이지네이션 --%>
    <ul class="pagination justify-content-center">
        <%-- 이전 --%>
        <%
            if (startPage != 1) {
        %>
        <li class="page-item">
            <a class="page-link"
               href="employeeList.jsp?currentPage=<%=startPage-1%>">
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
               href="employeeList.jsp?currentPage=<%=i%>"><%=i%>
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
               href="employeeList.jsp?currentPage=<%=endPage+1%>">다음</a>
        </li>
        <%
            }
        %>
    </ul>
</div>
<script>
    let active = document.querySelectorAll('.active-value');
    active.forEach(function (element) {
        element.addEventListener('change', function (e) {
            let activeValue = this.value;
            let id = this.getAttribute('emp-id');
            let url = '/adminList/employeeActiveAction.jsp?id=' + id + '&activeValue=' + activeValue;
            fetch(url, {
                method: "GET",
            })
                .then((response) => response.text())
                .then((data) => {
                    if (data.trim() == 'ok') {
                        alert('변경성공')
                    } else {
                        alert('변경실패')
                    }
                })
        })
    })
</script>
</body>
</html>
