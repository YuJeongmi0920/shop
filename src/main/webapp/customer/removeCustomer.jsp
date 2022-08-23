<%@ page import="dto.Customer" %>
<%@ page import="service.CustomerService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");

    Customer customer = new Customer();
    String Id = request.getParameter("Id");
    String Pass= request.getParameter("Pass");

    System.out.println("id = " + Id);
    System.out.println("pw = " + Pass);


    CustomerService customerService = new CustomerService();

    customer.setCustomerId(Id);
    customer.setCustomerPass(Pass);
    customerService.removeCustomer(customer);

    session.invalidate();
    response.sendRedirect("/login/loginForm.jsp");


%>
