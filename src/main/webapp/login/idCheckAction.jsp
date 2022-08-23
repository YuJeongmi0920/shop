
<%@ page import="service.SignService" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("utf-8");
    String ckId = request.getParameter("ckId");
    String check = request.getParameter("check");

    SignService signService = new SignService();


    System.out.println("ckId = " + ckId);
    if(check == null){
        if (signService.idCheck(ckId)) {

            response.sendRedirect("/customer/addCustomer.jsp?ckId=" + ckId);
            return;
        }
        response.sendRedirect("/customer/addCustomer.jsp?errorMsg=badId");
        return;
    }

    if (signService.idCheck(ckId)) {
        response.sendRedirect("/employee/addEmployee.jsp?ckId=" + ckId);
        return;
    }
    response.sendRedirect("/employee/addEmployee.jsp?errorMsg=badId");
    return;


%>