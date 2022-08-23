<%@ page import="dto.Employee" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Employee employee = (Employee) session.getAttribute("employee");
    if (employee == null) {
        out.print("<script>alert('권한없음');location.href='login/loginForm.jsp'</script>");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/goods/goodsInsertAction.jsp" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>
                상품명
            </td>
            <td>
                <input type="text" name="goodsName">
            </td>
        </tr>
        <tr>
            <td>
                상품가격
            </td>
            <td>
                <input type="text" name="goodsPrice">
            </td>
        </tr>
        <tr>
            <td>
                상품이미지
            </td>
            <td>
                <input type="file" name="file">
            </td>
        </tr>
        <tr>
            <td>
                상품판매여부
            </td>
            <td>
                <select name="soldOut">
                    <option value="N" selected>
                        N
                    </option>
                    <option value="Y">
                        Y
                    </option>
                </select>
            </td>
        </tr>
    </table>
    <button type="submit" id="btn">상품등록</button>
</form>
</body>
</html>
