<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="form-container">
    <table class="memberTable">
        <tr>
            <td>아 이 디</td>
            <td>
                <input type="text" name="login_id" size="20" id="login_id">
                <button type="button" name="idCheck" id="btnidCheck">중복확인</button>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <div id="idCheckMsg"></div>
            </td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td><input type="password" name="password" size="20" id='password'></td>
        </tr>
        <tr>
            <td>비밀번호확인</td>
            <td><input type="password" name="password_check" size="20" id='password_check'></td>
        </tr>
        <tr>
            <td>닉네임</td>
            <td><input type="text" name="nickname" size="20" id='nickname'>
        </tr>
        <tr>
            <td text-aline="center">
                <input type="submit" value="회원가입" onclick="join()">
            </td>
        </tr>
    </table>
</div>

<script type="text/javascript" src="/js/Members.js"></script>

<%@ include file="../layout/footer.jsp"%>