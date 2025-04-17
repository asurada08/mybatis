<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<link href="/css/mainCss.css" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
    <nav>
        <div class="header">
            <a href="/home" class="left">내맘대로 게시판</a>
            <ul class="right">
                <c:choose>
                    <c:when test="${empty loginUser}">
                        <li><a href="/loginForm">로그인</a></li>
                        <li><a href="/joinForm">회원가입</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/boards/writeForm">글쓰기</a></li>
                        <li>${sessionScope.nickname}님 반갑습니다</li>
                        <li><a href="/updateForm/${sessionScope.loginUser}">회원정보수정</li>
                        <li><a href="/logout">로그아웃</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
        <div class="nav">
            <ul>
                <li><a href="/home?categoryId=1">공지사항</a></li>
                <li><a href="/home?categoryId=2">음식관련</a></li>
                <li><a href="/home?categoryId=3">게임관련</a></li>
                <li><a href="/home?categoryId=4">영화관련</a></li>
                <li><a href="/home?categoryId=5">자유게시판</a></li>
                <li><a href="/home?categoryId=6">Q & A</a></li>
            </ul>
        </div>
    </nav>
