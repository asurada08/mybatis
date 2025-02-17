let id = $("#id").val();

$(document).ready(function(){
    $("#btnWrite").click(function(){
        console.log("Write clicked")
        write();
    });
});

function write(){
    let data = {
        member_id : $("#member_id"),
        title : $("#title").val(),
        content : $("#content").val(),
        category_id : $("#category_id").val()
    }

    $.ajax("/write", {
            type:"post",
            data:JSON.stringify(data),
            dataType:"json",
            headers: {
                        "Content-Type": "application/json; charset=utf-8"
            }
        }).done((res) => {
            if (res.code == 1) {
                alert("작성 완료");
                window.location.href = "http://localhost:8080/home";
            } else {
                alert("입력 오류");
            }
        });
}