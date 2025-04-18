<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="form-container">
    <table class="memberTable">
        <input type="hidden" value="${loginUser}" id="id">
        <tr>
            <td>아이디</td>
            <td><input type="text" value="${updateUserData.login_id}" id="login_id" readonly></td>
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
            <td><input type="text" name="nickname" id="nickname" value="${updateUserData.nickname}"></td>
        </tr>
        <tr>
            <td colspan="2" text-align="center">
                <input type="submit" value="확인" id="btnUpdate">
                <input type="button" value="회원탈퇴" id="btnDelete">
            </td>
        </tr>
    </table>
</div>

<script type="text/javascript" src="/js/Members.js"></script>

<script>
$(document).ready(function(){
   let loginUserId = $("#loginUserId").val();
   let loginId = $("#login_id").val();

   if (loginUserId == "" || loginId == "") {
     alert("잘못된 접근입니다.");
      window.history.back();
   }
});
</script>

<%@ include file="../layout/footer.jsp"%>