<%@ page import="java.net.URLEncoder" %>
<%@ page import="dto.Employee" %>
<%@ page import="service.SignService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    request.setCharacterEncoding("utf-8");

    Employee employee = new Employee();
    String employeeId = request.getParameter("employeeId");
    String employeePass = request.getParameter("employeePass");
    String employeeName = request.getParameter("employeeName");

    System.out.println("employeeId = " + employeeId);
    System.out.println("employeePass= " + employeePass);
    System.out.println("employeeName = " + employeeName);

    employee.setEmployeeId(employeeId);
    employee.setEmployeePass(employeePass);
    employee.setEmployeeName(employeeName);

    System.out.println("employee = " + employee);
    SignService signService = new SignService();

    if (!signService.insertEmployee(employee)) {
        String errorMsg = "없는 아이디거나 정보가 잘못되었습니다";
        String encode = URLEncoder.encode(errorMsg, "UTF-8");
        response.sendRedirect("/login/loginForm.jsp?errorMsg2=" + encode);
        return;
    }
    response.sendRedirect("/login/loginForm.jsp");
%>
