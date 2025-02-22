<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
    <div class="board-detail">
        <div class="board-info">
            <div class="info-item"><strong>카테고리:</strong> ${detailDto.category_name}</div>
            <div class="info-item"><strong>제목:</strong> ${detailDto.title}</div>
            <div class="info-item"><strong>작성자:</strong> ${detailDto.nickname}</div>
            <div class="info-item"><strong>작성일:</strong> ${detailDto.created_at} / <strong>수정일:</strong> ${detailDto.updated_at}</div>
            <div class="info-item"><strong>조회수:</strong> ${detailDto.view_cnt}</div>
        </div>
        <div class="info-item"><strong>내용:</strong>
            <div class="board-content">
                ${detailDto.content}
            </div>
        </div>
    </div>

    <c:if test="${loginUser.id == detailDto.members_id}">
        <div class="button-group"><a href="/boards/updateForm/${detailDto.id}">수정</a></div>
    </c:if>
</div>

<script type="text/javascript" src="/js/Boards.js"></script>

<%@ include file="../layout/footer.jsp"%>
