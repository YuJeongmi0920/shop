
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
</head>
<body>
<!-- id ck form -->
<form action="/login/idCheckAction.jsp" method="post">
  <div>
    ID 체크
    <input type="text" name="ckId">
    <input type="hidden" name="check" value="employee">
    <button type="submit">아이디중복검사</button>
  </div>
</form>
<% if (request.getParameter("errorMsg") != null) {%>
중복된 아이디입니다. 다시 시도해주세요.
<%} %>
<!-- 고객가입 form -->
<%
  String ckId = "";
  if (request.getParameter("ckId") != null) {
    ckId = request.getParameter("ckId");
  }
%>
<form action="/employee/insertEmployeeAction.jsp" method="post">
  <table border="1">
    <tr>
      <td>아이디</td>
      <td>
        <input type="text" name="employeeId" id="employeeId"
               readonly="readonly" value="<%=ckId%>">
      </td>
    <tr>
      <td>비밀번호</td>
      <td><input type="password" name="employeePass"></td>
    </tr>
    <tr>
      <td>이름</td>
      <td><input type="text" name="employeeName"></td>
    </tr>


  </table>
  <button type="submit">회원가입</button>
  <button type="reset">초기화</button>
</form>
<% if (request.getParameter("custErrorMsg") != null) {%>
회원가입 실패
<% }%>
<a href="/login/loginForm.jsp">돌아가기</a>
</div>
</table>
</form>
</body>
</html>
