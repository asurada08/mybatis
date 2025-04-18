<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
    <div class="input-group">
        <input type="hidden" id="members_id" value="${loginUser}">
        <select id="category_id" name="category_id" size="1">
            <option value="">게시판을 선택해 주세요</option>
            <option value="1">공지사항</option>
            <option value="2">음식관련</option>
            <option value="3">게임관련</option>
            <option value="4">영화관련</option>
            <option value="5">자유게시판</option>
            <option value="6">Q & A</option>
        </select>
        <input type="text" id="title">
    </div>
    <div class="input-group">
        <textarea id="content" rows="8"></textarea>
    </div>
    <div class="button-group">
        <button type="button" id="btnWrite">등록</button>
    </div>
</div>

<script type="text/javascript" src="/js/Boards.js"></script>

<%@ include file="../layout/footer.jsp"%>