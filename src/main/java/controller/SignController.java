package controller;

import service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/sign/id-check")
public class SignController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("중복체크");
        String id = request.getParameter("id");
        CustomerService customerService = new CustomerService();
        if (customerService.idCheck(id)) {
            response.getWriter().print("ok");
        } else {
            response.getWriter().print("fail");
        }
    }
}
