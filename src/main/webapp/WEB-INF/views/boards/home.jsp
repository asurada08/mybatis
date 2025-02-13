<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOC TYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="/js/Boards.js"></script>
<script type="text/javascript" src="/js/Members.js"></script>
</head>
<body>
    <nav>
        <div>
            <a href="/home">k_board</a>
        </div>
        <div>
            <ul>
                <c:choose>
                    <c:when test="${empty loginUser}">
                        <li><a href="loginForm">로그인</a></li>
                        <li><a href="joinForm">회원가입</a></li>
                    </c:when>
                    <c:otherwise>
                        <li>글쓰기</li>
                        <li>${loginUser.nickname}님 반갑습니다</li>
                        <li><a href="/updateForm/${loginUser.id}">회원정보수정</li>
                        <li><a href="logout">로그아웃</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>
    <div>
        <div>
            검색기능 부분
        </div>
        <table>
            <thead>
                <tr>
                    <th>글번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th></th>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>