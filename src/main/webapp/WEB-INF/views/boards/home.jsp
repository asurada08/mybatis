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
        <c:if test="${boardsList.size() > 0}">
            <c:forEach var="boards" items="${boardsList}">
                <tr>
                    <th>${boards.id}</th>
                    <td><a href="javascript:void(0);" onclick="viewCnt(${boards.id})" class="title">${boards.title}</a></td>
                    <td>${boards.nickname}</td>
                    <td>${boards.created_at}</td>
                    <td>${boards.view_cnt}</td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${boardsList.size() == 0}">
            <tr>
                <td/>
                <td/>
                <td> 게시글 없음</td>
                <td/>
                <td/>
            </tr>
        </c:if>
        </tbody>
    </table>
    <div class="paging">
        <c:if test="${not empty paging}">
            <ul class="pagination justify-content-center">
                <li class="page-item ${paging.hasPrevious ? '' : 'disabled'}">
                    <a class="page-link" href="?page=${paging.pageNumber - 1}&searchType=${param.searchType}&keyword=${param.keyword}">
                        <span>이전</span>
                    </a>
                </li>

                <c:forEach var="page" begin="1" end="${paging.totalPages}">
                    <c:if test="${page >= paging.pageNumber - 5 && page <= paging.pageNumber + 5}">
                        <li class="page-item ${page == paging.pageNumber ? 'active' : ''}">
                            <a class="page-link" href="?page=${page}&searchType=${param.searchType}&keyword=${param.keyword}">${page}</a>
                        </li>
                    </c:if>
                </c:forEach>

                <li class="page-item ${paging.hasNext ? '' : 'disabled'}">
                    <a class="page-link" href="?page=${paging.pageNumber + 1}&searchType=${param.searchType}&keyword=${param.keyword}">
                        <span>다음</span>
                    </a>
                </li>
            </ul>
        </c:if>
    </div>
    <div class="search">
        <select id="searchType" class="searchSelect">
            <option value="title_content" ${param.searchType == 'title_content' ? 'selected' : ''}>제목+내용</option>
            <option value="author" ${param.searchType == 'author' ? 'selected' : ''}>작성자</option>
        </select>
        <input type="text" id="keyword" value="${param.keyword}" class="searchKeyword" placeholder="검색어를 입력하세요" />
        <button id="searchBtn" class="searchBtn">검색</button>
    </div>
</div>

<script type="text/javascript" src="/js/Boards.js"></script>

<%@ include file="../layout/footer.jsp"%>