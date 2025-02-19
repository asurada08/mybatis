<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

    <table>
        <input type="hidden" value="${loginUser.id}" id="id">
        <tr>
            <td>아이디</td>
            <td><input type="text" value="${loginUser.login_id}" id="login_id" readonly></td>
        </tr>
        <tr>
            <td>변경 비밀번호</td>
            <td><input type="password" name="password" id="password"></td>
        </tr>
        <tr>
            <td>비밀번호 확인</td>
            <td><input type="password" name="password_check" id="password_check"></td>
        </tr>
        <tr>
            <td>닉네임</td>
            <td><input type="text" name="nickname" id="nickname" value="${loginUser.nickname}"></td>
        </tr>
        <tr>
            <td colspan="2" text-align="center">
                <input type="submit" value="확인" id="btnUpdate">
                <input type="button" value="회원탈퇴" id="btnDelete">
            </td>
        </tr>
    </table>

<%@ include file="../layout/footer.jsp"%>