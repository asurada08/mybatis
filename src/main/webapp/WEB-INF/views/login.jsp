<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOC TYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>
    <form name="login" method="post" action="login">
        아  이  디 : <input type="text" id="login_id" name="login_id"><br>
        비 밀 번 호 : <input type="password" id="login_password" name="login_password">
        <input type="submit" value="로그인">
    </form>
</body>
</html>