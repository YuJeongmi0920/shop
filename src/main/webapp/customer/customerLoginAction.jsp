<%@ page import="java.net.URLEncoder" %>
<%@ page import="dto.Customer" %>
<%@ page import="service.CustomerService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    request.setCharacterEncoding("utf-8");

    Customer customer = new Customer();
    String customerId = request.getParameter("customerId");
    String customerPass = request.getParameter("customerPass");

    System.out.println("customerId = " + customerId);
    System.out.println("customerPass = " + customerPass);


    customer.setCustomerId(customerId);
    customer.setCustomerPass(customerPass);

    CustomerService customerService = new CustomerService();
    Customer customerByIdAndPw = customerService.getCustomerByIdAndPw(customer);

    if (customerByIdAndPw.getCustomerId() == null) {
        String errorMsg = "없는 아이디거나 정보가 잘못되었습니다";
        String encode = URLEncoder.encode(errorMsg, "UTF-8");
        response.sendRedirect("/login/loginForm.jsp?errorMsg=" + encode);
        return;
    }

    session.setAttribute("customer", customerByIdAndPw);
    response.sendRedirect("/login/loginSucUser.jsp");

%>
