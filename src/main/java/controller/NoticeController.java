package controller;


import dto.Employee;
import dto.Notice;
import dto.PageNationDto;
import service.NoticeService;
import util.PageNationUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/notice/*")
public class NoticeController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        String context = request.getContextPath();
        String subAddr = uri.substring(context.length());
        NoticeService noticeService = new NoticeService();
        PageNationDto pageNation;

        int total;
        switch (subAddr) {
            case "/notice/notice-list":
                String current = request.getParameter("currentPage");


                // 페이지네이션
                total = noticeService.getNoticeListTotal();
                pageNation = PageNationUtil.getPageNation(current, total, "/notice/notice-list");
                request.setAttribute("pageNation", pageNation);


                // 리스트
                List<Notice> noticeList = noticeService.getNoticeList(pageNation.getRowPerPage(), pageNation.getBeginRow());
                System.out.println(noticeList);
                request.setAttribute("list", noticeList);


                // 포워드
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/notice/notice-list.jsp");
                requestDispatcher.forward(request, resp);
                break;

            case "/notice/notice-detail":
                int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
                Notice notice = noticeService.getNoticeReadDetail(noticeNo);

                System.out.println("------------------------------------------------------= " + noticeNo);
                request.setAttribute("notice", notice);


                // 포워드
                RequestDispatcher requestDispatcher2 = request.getRequestDispatcher("/WEB-INF/notice/notice-detail.jsp");
                requestDispatcher2.forward(request, resp);
                break;


            case "/notice/notice-insert":
                String noticeTitle = request.getParameter("noticeTitle");
                String noticeContent = request.getParameter("noticeContent");
                HttpSession session = request.getSession();
                Employee employee = (Employee) session.getAttribute("employee");
                System.out.println("loginMember = " + employee);
                noticeService = new NoticeService();
                notice = new Notice();

                notice.setNoticeTitle(noticeTitle);
                notice.setNoticeContent(noticeContent);
                int row = noticeService.addNoticeList(notice);
                System.out.println("row : " + row);
                // 포워드
                resp.sendRedirect("/notice/notice-list");
                break;

            //입력 폼으로
            case "/notice/notice-insert-form":
                requestDispatcher = request.getRequestDispatcher("/WEB-INF/notice/notice-insert.jsp");
                requestDispatcher.forward(request, resp);
                break;


        }

    }
}
