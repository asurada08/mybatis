let id = $("#id").val();

$(document).ready(function(){
    $("#btnWrite").click(function(){
        console.log("B Write clicked");
        write();
    });
});

function write(){
    let member_id = $("#member_id").val();
    let title = $("#title").val();
    let content = $("#content").val();
    let category_id = $("#category_id").val();

    let data = {
        member_id : member_id,
        title : title,
        content : content,
        category_id : category_id
    }

    console.log("title : ", $("#title").val());
    console.log("category_id : ", $("#category_id").val());

    $.ajax("/boards/write" , {
            type:"post",
            data:JSON.stringify(data),
            dataType:"json",
            headers: {
                        "Content-Type": "application/json; charset=utf-8"
            }
        }).done((res) => {
            console.log(res);
            if (res.code == 1) {
                alert("작성 완료");
                window.location.href = "http://localhost:8080/home";
            } else {
                alert("입력 오류");
            }
        });
}

$(document).ready(function(){
    $("#btnUpdateB").click(function(){
        console.log("B Update clicked");
        update();
    });
});

function update(){
    let id = $("#id").val();
    console.log(id);

    if (!id) {
        alert("id값 문제");
        return;
    }

    let data = {
        id : id,
        title : $("#title").val(),
        content : $("#content").val(),
        category_id : $("#category_id").val()
    }

    $.ajax("/boards/update/" + id , {
            type:"post",
            data:JSON.stringify(data),
            dataType:"json",
            headers: {
                        "Content-Type": "application/json; charset=utf-8"
            }
        }).done((res) => {
            console.log(res);
            if (res.code == 1) {
                alert("수정 완료");
                window.location.href = "http://localhost:8080/home";
            } else {
                alert("입력 오류 : " + (res.message || res.error || "알 수 없는 오류"));
            }
        });
}

$(document).ready(function(){
    $("#btnDeleteB").click(function(){
        console.log("B delete clicked")
        cancel();
    });
});

function cancel(){
    let id = $("#id").val();

    let data = {
        del_yn : "Y"
    }

    $.ajax("/boards/delete/" + id , {
        type:"post",
        data:JSON.stringify(data),
        dataType:"json",
        headers: {
                    "Content-Type": "application/json; charset=utf-8"
        }
    }).done((res) => {
        if (res.code == 1) {
            alert("수정 완료");
            window.location.href = "http://localhost:8080/home";
        } else {
            alert("입력 오류");
        }
    });
}

function viewCnt(id) {
    $.ajax({
        url: '/boards/viewCnt/' + id,
        type: 'GET',
        success: function(response) {
            console.log("조회수 증가");
            window.location.href = "/boards/detail/" + id;
        },
        error: function(xhr, status, error) {
            console.error("조회수 증가 실패: ", error);
        }
    });
}
