<%@ page import="dto.Employee" %>
<%@ page import="service.EmployeeService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Employee employee = new Employee();
    String Id = request.getParameter("Id");
    String Pass = request.getParameter("Pass");

    System.out.println("employeeId = " + Id);
    System.out.println("employeePass = " + Pass);


    EmployeeService employeeService = new EmployeeService();

    employee.setEmployeeId(Id);
    employee.setEmployeePass(Pass);
    employeeService.removeEmployee(employee);

    session.invalidate();
    response.sendRedirect("/login/loginForm.jsp");


%>
