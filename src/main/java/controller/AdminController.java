package controller;

import dto.Customer;
import dto.Employee;
import dto.PageNationDto;
import service.CustomerService;
import service.OrderService;
import util.PageNationUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/adminList/*")
public class AdminController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String context = request.getContextPath();
        String subAddr = uri.substring(context.length());
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();
        PageNationDto pageNation;
        int total;
        switch (subAddr) {
            case "/adminList/customer-list":
                System.out.println("헬로우");
                String current = request.getParameter("currentPage");

                // 페이지네이션
                total = customerService.getCustomerListTotal();
                pageNation = PageNationUtil.getPageNation(current, total, "/adminList/customer-list");
                request.setAttribute("pageNation", pageNation);


                // 리스트
                List<Customer> customerList = customerService.getCustomerList(pageNation.getRowPerPage(), pageNation.getBeginRow());
                request.setAttribute("list", customerList);

                // 포워드
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/adminList/customerList.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "/adminList/customer-detail":
                HttpSession session = request.getSession();
                Employee employee = (Employee) session.getAttribute("employee");
                Customer customer = (Customer) session.getAttribute("customer");
                String customerId = request.getParameter("customerId");
                if (customer != null) {
                    customerId = customer.getCustomerId();
                } else {
                    if (employee == null) {
                        PrintWriter writer = response.getWriter();
                        writer.print("<script>alert('권한없음');location.href='login/loginForm.jsp'</script>");
                        return;
                    }
                }
                current = request.getParameter("currentPage");
                // 페이지네이션
                total = orderService.getCustomerOrderListTotal(customerId);
                pageNation = PageNationUtil.getPageNation(current, total, "/adminList/customer-detail");
                request.setAttribute("pageNation", pageNation);
                request.setAttribute("list", orderService.getCustomerOrderList(customerId, pageNation.getRowPerPage(), pageNation.getBeginRow()));
                // 포워드
                requestDispatcher = request.getRequestDispatcher("/WEB-INF/adminList/customerDetail.jsp");
                requestDispatcher.forward(request, response);
                break;
        }
    }
}
