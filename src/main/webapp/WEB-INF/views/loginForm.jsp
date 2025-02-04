<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOC TYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<script type="text/javascript" src="/js/Members.js"></script>
</head>
<body>
    <form name="login" method="post" action="login">
        <table>
            <tr>
                <td>아 이 디</td>
                <td><input type="text" name="login_id" value="${login_id}"></td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td><input type="password" name="password"></td>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="로그인" onclick="return loginCheck()">
                    <input type="button" value="회원가입" onclick="location.href='joinForm'">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>