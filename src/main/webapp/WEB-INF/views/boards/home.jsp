<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

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
                    <th>조회수</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="boards" items="${boardsList}">
                    <tr>
                        <th>${boards.id}</th>
                        <td>${boards.title}</td>
                        <td>${boards.nickname}</td>
                        <td>${boards.created_at}</td>
                        <td>${boards.view_cnt}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

<%@ include file="../layout/footer.jsp"%>