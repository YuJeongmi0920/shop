<%@ page import="java.net.URLEncoder" %>
<%@ page import="dto.Employee" %>
<%@ page import="service.EmployeeService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    Employee employee = new Employee();
    String employeeId = request.getParameter("employeeId");
    String employeePass = request.getParameter("employeePass");


    System.out.println("employeeId = " + employeeId);
    System.out.println("employeePass = " + employeePass);

    employee.setEmployeeId(employeeId);
    employee.setEmployeePass(employeePass);

    EmployeeService employeeService = new EmployeeService();
    Employee employeeByIdAndPw = employeeService.getEmployeeByIdAndPw(employee);

    if (employeeByIdAndPw.getEmployeeId() == null) {
        String errorMsg = "없는 아이디거나 정보가 잘못되었습니다";
        String encode = URLEncoder.encode(errorMsg, "UTF-8");
        response.sendRedirect("/login/loginForm.jsp?errorMsg2=" + encode);
        return;
    }
    session.setAttribute("employee", employeeByIdAndPw);
    System.out.println(employeeByIdAndPw);
    response.sendRedirect("/login/loginSucAdmin.jsp");
%>