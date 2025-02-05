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

function join(){
    let login_id = $("#login_id").val();
    let password = $("#password").val();
    let password_check = $("#password_check").val();
    let nickname = $("#nickname").val();

    if(login_id.length < 2){
        alert("아이디는 2글자 이상이어야 합니다");
//        $("#login_id").focus();
        return false;
    }

    if(password == ''){
        alert("비밀번호를 입력해주세요");
//        join.password.focus();
        return false;
    }

    if(password != password_check){
        alert("비밀번호가 일치하지 않습니다");
//        join.password.focus();
        return false;
    }

    let params = {
        login_id: login_id,
        password: password,
        nickname: nickname
    }

    $.ajax("/join", {
        type: "POST",
        dataType: "json",
        data: JSON.stringify(params),
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        }
        }).done((res) => {
            let result = res.result;
            if (result === "ok") {
                window.location.href = "http://localhost:8080/loginForm";
            } else {
                alert("똑바로 써라")
            }
        });
}
