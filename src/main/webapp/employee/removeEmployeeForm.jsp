<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<form action="<%=request.getContextPath()%>./removeEmployee.jsp" method="post">
  아이디 <input type="text" name="Id">
  비밀번호 <input type="password" name="Pass">
  <input type="submit" value="회원탈퇴">
</form>

</body>
</html>