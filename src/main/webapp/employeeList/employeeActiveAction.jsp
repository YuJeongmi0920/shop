<%@ page import="service.EmployeeService" %>
<%@ page import="dto.Employee" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String id = request.getParameter("id");
    String activeValue = request.getParameter("activeValue");
    EmployeeService employeeService = new EmployeeService();
    Employee employee = new Employee();
    employee.setEmployeeId(id);
    employee.setActive(activeValue);
    int num = employeeService.modifyEmployeeActive(employee);
    if (num == 0) {
        out.print("fail");
    }
    out.print("ok");
%>
