<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
    <table class="boardsTable">
        <thead>
            <tr>
                <th>글번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조회수</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="boards" items="${boardsList}">
                <tr>
                    <th>${boards.id}</th>
                    <td><a href="javascript:void(0);" onclick="viewCnt(${boards.id})" class="title">${boards.title}</a></td>
                    <td>${boards.nickname}</td>
                    <td>${boards.created_at}</td>
                    <td>${boards.view_cnt}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div>
        검색기능 부분
    </div>
</div>

<script type="text/javascript" src="/js/Boards.js"></script>

<%@ include file="../layout/footer.jsp"%>