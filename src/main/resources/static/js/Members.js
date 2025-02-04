function loginCheck(){
    if (document.login.login_id.value.length == 0){
        alert("아이디를 입력해주세요");
        login.login_id.focus();
        return false;
    }
    if (document.login.password.value == ''){
        alert("비밀번호를 입력해주세요");
        login.password.focus();
        return false;
    }
    return true;
}

function joinCheck(){
    if(document.join.login_id.value == 0){
        alert("아이디를 입력해 주세요");
        join.login_id.focus();
        return false;
    }
    if(document.join.login_id.value < 2){
        alert("아이디는 2글자 이상이어야 합니다");
        join.login_id.focus();
        return false;
    }
    if(document.join.password.value == ''){
        alert("비밀번호를 입력해주세요");
        join.password.focus();
        return false;
    }
    if(document.join.password.value != document.join.password_check.value){
        alert("비밀번호가 일치하지 않습니다");
        join.password.focus();
        return false;
    }
    return true;
}
