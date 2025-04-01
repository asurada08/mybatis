<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
    <div class="input-group">
        <input type="hidden" id="id" value="${boardsDtoUpdate.id}">
        <input type="hidden" id="boardsDtoUpdate" value="${boardsDtoUpdate}">
        <input type="hidden" id="members_id" value="${loginUser}">
        <select id="category_id" name="category_id" size="1">
            <option value="">게시판을 선택해 주세요</option>
            <option value="1" ${boardsDtoUpdate.category_id == 1 ? 'selected' : ''}>공지사항</option>
            <option value="2" ${boardsDtoUpdate.category_id == 2 ? 'selected' : ''}>음식관련</option>
            <option value="3" ${boardsDtoUpdate.category_id == 3 ? 'selected' : ''}>게임관련</option>
            <option value="4" ${boardsDtoUpdate.category_id == 4 ? 'selected' : ''}>영화관련</option>
            <option value="5" ${boardsDtoUpdate.category_id == 5 ? 'selected' : ''}>자유게시판</option>
            <option value="6" ${boardsDtoUpdate.category_id == 6 ? 'selected' : ''}>Q & A</option>
        </select>
        <input type="text" id="title" value="${boardsDtoUpdate.title}">
    </div>
    <div class="input-group">
        <textarea id="content" rows="8">${boardsDtoUpdate.content}</textarea>
    </div>
    <div class="button-group">
        <button type="button" id="btnUpdateB">수정</button>
        <button type="button" id="btnDeleteB">삭제</button>
    <div>
</div>

<script type="text/javascript" src="/js/Boards.js"></script>

<script>
$(document).ready(function(){
        let boardsDtoUpdate = $("#boardsDtoUpdate").val();
        if (boardsDtoUpdate == "") {
            alert("잘못된 접근입니다.");
            window.history.back();
        }
    });
</script>

<%@ include file="../layout/footer.jsp"%>