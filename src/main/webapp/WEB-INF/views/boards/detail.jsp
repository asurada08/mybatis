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
        <c:if test="${loginUser == detailDto.members_id}">
            <div class="button-group"><a href="/boards/updateForm/${detailDto.id}">수정</a></div>
        </c:if>
    </div>

    <c:forEach var="comment" items="${comments}">
        <c:if test="${comment.reparent == 0}">
            <div class="comment">
                <p><strong>작성자:</strong> ${comment.nickname}</p>
                <p><strong>작성일:</strong> ${comment.created_at}</p>
                <p><strong>댓글:</strong> <span class="comment-content" id="comment-content-${comment.id}">${comment.content}</span></p>

                <c:if test="${loginUser == comment.members_id}">
                    <div><a href="javascript:void(0);" class="updateBtn" id="updateBtn-${comment.id}">수정</a></div>
                    <div><a href="javascript:void(0);" class="deleteBtn" id="deleteBtn-${comment.id}">삭제</a></div>
                </c:if>

                <div><a href="javascript:void(0);" class="replyBtn" id="${comment.id}">답글</a></div>

                <div class="reply-form-${comment.id}" style="display: none;">
                    <input type="hidden" id="reply-boards_id-${comment.id}" name="boards_id" value="${detailDto.id}">
                    <input type="hidden" id="reply-members_id-${comment.id}" name="members_id" value="${loginUser}">
                    <input type="hidden" id="reply-reparent-${comment.id}" name="reparent" value="${comment.id}">
                    <textarea id="reply-content-${comment.id}" rows="4" cols="50" placeholder="답글을 작성해주세요."></textarea>
                    <button class="replyWriteBtn" id="replyWriteBtn-${comment.id}">답글 작성</button>
                </div>

                <div class="update-form" id="update-form-${comment.id}" style="display: none;">
                    <textarea id="update-content-${comment.id}" rows="4" cols="50">${comment.content}</textarea>
                    <button class="save-update-btn" id="save-update-btn-${comment.id}">수정 저장</button>
                    <button class="cancel-update-btn" id="cancel-update-btn-${comment.id}">취소</button>
                </div>

            </div>

            <c:forEach var="replyComment" items="${comments}">
                <c:if test="${replyComment.reparent == comment.id}">
                    <div class="reply-comment" style="margin-left: 20px;">
                        <p><strong>작성자:</strong> ${replyComment.nickname}</p>
                        <p><strong>작성일:</strong> ${replyComment.created_at}</p>
                        <p><strong>댓글:</strong><span class="comment-content" id="comment-content-${replyComment.id}">${replyComment.content}</span></p>
                        <c:if test="${loginUser == replyComment.members_id}">
                           <div><a href="javascript:void(0);" class="updateBtn" id="updateBtn-${replyComment.id}">수정</a></div>
                           <div><a href="javascript:void(0);" class="deleteBtn" id="deleteBtn-${replyComment.id}">삭제</a></div>
                        </c:if>

                        <div class="update-form" id="update-form-${replyComment.id}" style="display: none;">
                           <textarea id="update-content-${replyComment.id}" rows="4" cols="50">${replyComment.content}</textarea>
                           <button class="save-update-btn" id="save-update-btn-${replyComment.id}">수정 저장</button>
                           <button class="cancel-update-btn" id="cancel-update-btn-${replyComment.id}">취소</button>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </c:if>
    </c:forEach>

    <div class="comment-form">
        <input type="hidden" id="boards_id" name="boards_id" value="${detailDto.id}"></input>
        <input type="hidden" id="members_id" name="members_id" value="${loginUser}"></input>
        <input type="hidden" id="reparent" name="reparent" value="0"></input>
        <textarea id="content" rows="4" cols="50" placeholder="댓글을 작성해주세요."></textarea>
        <button id="commentBtn">댓글 작성</button>
    </div>

</div>

<script type="text/javascript" src="/js/Boards.js"></script>
<script type="text/javascript" src="/js/Comments.js"></script>

<%@ include file="../layout/footer.jsp"%>

