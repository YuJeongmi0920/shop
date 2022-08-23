<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">
    <form action="/notice/notice-insert" method="post">
        <div class="mb-3">
            <label for="noticeTitle" class="form-label">제목</label>
            <input type="text" class="form-control" id="noticeTitle" name="noticeTitle">
        </div>
        <div class="mb-3">
            <label for="noticeContent" class="form-label">내용</label>
            <textarea class="form-control" name="noticeContent" id="noticeContent" rows="5"></textarea>
        </div>
        <div>
            <input type="submit" href="/notice/notice-insert" class="btn btn-primary" value="입력">
            <input type="submit" href="/notice/notice-list" class="btn btn-primary" value="취소">
        </div>
    </form>
</div>

</body>
</html>

