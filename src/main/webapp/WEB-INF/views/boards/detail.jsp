<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div>
    <c:if test="${loginUser.id == detailDto.members_id}">
        <div><a href="/boards/updateForm/${detailDto.id}">수정</a></div>
    </c:if>
    <div><input type="hidden" value="${detailDto.id}" id="id"></div>
    <div>${detailDto.category_name}</div>
    <div>${detailDto.title}</div>
    <div>${detailDto.nickname}</div>
    <div>${detailDto.created_at} / ${detailDto.updated_at}</div>
    <div>${detailDto.view_cnt}</div>
    <div>${detailDto.content}</div>
</div>
<%@ include file="../layout/footer.jsp"%>