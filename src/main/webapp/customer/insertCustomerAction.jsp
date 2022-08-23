<%@ page import="dto.Customer" %>
<%@ page import="service.SignService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("utf-8");
    Customer customer = new Customer();

    String customerId = request.getParameter("customerId");
    String customerPw = request.getParameter("customerPw");
    String customerName = request.getParameter("customerName");
    String customerAddress = request.getParameter("customerAddress");
    String customerPhoneNumber = request.getParameter("customerPhoneNumber");

    // soutv
    System.out.println("customerId= " + customerId);
    System.out.println("pw = " + customerPw);
    System.out.println("name = " + customerName);
    System.out.println("address = " + customerAddress);
    System.out.println("phoneNumber = " + customerPhoneNumber);


    customer.setCustomerId(customerId);
    customer.setCustomerPass(customerPw);
    customer.setCustomerName(customerName);
    customer.setCustomerAddress(customerAddress);
    customer.setCustomerTelephone(customerPhoneNumber);


    SignService customerService = new SignService();


    //debugging
    System.out.println("--------회원가입-------");
    if (customerService.insertCustomer(customer)) {
        //debugging
        System.out.println("회원가입 여부 : 성공");
        //redirection
        response.getWriter().println("<script>alert('가입성공');location.href='/login/loginForm.jsp'</script>");
    } else {
        //debugging
        System.out.println("회원가입 여부 : 실패");
        //redirection
        response.getWriter().println("<script>alert('가입실패');location.href='/login/loginForm.jsp'</script>");
    }
%>