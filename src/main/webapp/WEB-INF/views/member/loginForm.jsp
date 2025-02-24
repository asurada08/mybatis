<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="form-container">
    <table class="memberTable">
        <tr>
            <td>아 이 디</td>
            <td><input type="text" name="login_id" id="login_id"></td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td><input type="password" name="password" id="password"></td>
        <tr>
            <td colspan="2" >
                <input type="submit" value="로그인" id="btnLogin">
                <input type="button" value="회원가입" id="btnJoin" onclick="location.href='joinForm'">
            </td>
        </tr>
    </table>
</div>

<script type="text/javascript" src="/js/Members.js"></script>

<%@ include file="../layout/footer.jsp"%>