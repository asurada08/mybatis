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
    <form name="join" method="post" action="join">
        <table>
            <tr>
                <td>아 이 디</td>
                <td>
                    <input type="text" name="login_id" size="20" id="login_id">
                </td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td><input type="password" name="password" size="20"></td>
            </tr>
            <tr>
                <td>비밀번호확인</td>
                <td><input type="password" name="password_check" size="20"></td>
            </tr>
            <tr>
                <td>닉네임</td>
                <td><input type="text" name="nickname" size="20">
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="회원가입" onclick="return joinCheck()">
                    <input type="reset" value="취소">
                </td>
            </tr>
        </form>
</body>
</html>