<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script defer src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script defer src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <!-- Popper JS -->
    <script defer src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div>
        <div class="mb-3">
            <h2 class="form-label">제목</h2>
            <hr/>
            ${notice.noticeTitle}
        </div>
        <div class="mb-3">
            <h2 class="form-label">내용</h2>
            <hr/>
            ${notice.noticeContent}
        </div>
        <div class="mb-3">
            <h2 for="date" class="form-label">작성일</h2>
            <input type="text" class="form-control" id="date" name="date" value="${notice.createDate}" disabled>
        </div>
        <div>
            <a href="/notice/notice-list" class="btn btn-primary">리스트로</a>
        </div>
    </div>
</div>
</body>
</html>

