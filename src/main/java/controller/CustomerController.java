package controller;

import dto.Customer;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/customer-buy")
public class CustomerController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goodsNo = request.getParameter("goodsNo");
        String goodsCount = request.getParameter("goodsCount");
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        OrderService orderService = new OrderService();
        int num = orderService.insertOrder(goodsNo, customer, goodsCount);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        if (num == 0) {
            writer.print("<script>alert('구매실패 다시시도바람');location.href='/goods/customerGoodsList.jsp'</script>");
            return;
        }
        writer.print("<script>alert('주문완료');location.href='/goods/customerGoodsList.jsp'</script>");
    }
}
