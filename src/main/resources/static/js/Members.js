let idCheckPass = false;

$(document).ready(function(){
    $("#btnLogin").click(function(){
        console.log("login");
        login();
    });
});

function login(){
    let login_id = $("#login_id").val();
    let password = $("#password").val();

    if (login_id == ""){
        alert("아이디를 입력해주세요");
        return false;
    }

    if (password == ""){
        alert("비밀번호를 입력해주세요");
        return false;
    }

    let data = {
        login_id : login_id,
        password : password
    };

    console.log(JSON.stringify(data));

    $.ajax("/login", {
                type: "POST",
                dataType: "json",
                data: JSON.stringify(data),
                headers: {
                    "Content-Type": "application/json; charset=utf-8"
                }
        }).done(function(data){
            if (data.status === "success") {
                alert("로그인 완료");
                location.href = "/home";
            } else {
                alert(data.message);
            }
        }).fail(function(error){
            alert("서버 오류 발생");
        });
}

function join(){
    let login_id = $("#login_id").val();
    let password = $("#password").val();
    let password_check = $("#password_check").val();
    let nickname = $("#nickname").val();

    if(login_id == ""){
        alert("아이디를 입력해주세요");
//        $("#login_id").focus();
        return false;
    }

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

    if (idCheckPass == false) {
        alert("중복확인 해주세요")
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
                alert("똑바로 써라");
            }
        });
}

$(document).ready(function(){
    $("#btnidCheck").click(function(){
        console.log("clicked");
        idCheck();
    });
});

function idCheck(){
    let login_id = $("#login_id").val();

    if (login_id == "" || login_id.length == 0){
        $("#idCheckMsg").text("아이디를 입력하세요");
        return false;
    }

    console.log("아이디 값 : " + login_id);

    $.ajax({
        type : "get",
        url : "/idCheck",
        data : {"login_id" : login_id},
        dataType : "json",
        success: function(data){
            console.log("응답 데이터 : " + data);
            if (data == 1){
                $("#idCheckMsg").text("사용중인 아이디 입니다");
            } else {
                idCheckPass = true;
                $("#idCheckMsg").text("사용가능한 아이디 입니다")
            }
        },
        error : function(e){
            console.log(e)
        }
    });
}