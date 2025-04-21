$('#commentBtn').click(function() {
    let content = $('#content').val();

    console.log("content :: " + content);
    console.log("content :: " + content);
    let boards_id = $("#boards_id").val();
    let members_id = $("#members_id").val();
    let params = {
                  boards_id: boards_id,
                  members_id: members_id,
                  content: content,
                  reparent: 0
                }

    console.log("params :: ",params);
    $.ajax({
        url: '/boards/writeComment',
        type: 'POST',
        data: JSON.stringify(params),
        dataType:"json",
        contentType: 'application/json; charset=utf-8',
        success: function(response) {
            let id = $("#boards_id").val();
            window.location.href = '/boards/detail/' + id;
        },
        error: function() {
            alert("댓글 작성에 실패했습니다.");
        }
    });
});

document.querySelectorAll('.replyBtn').forEach(button => {
    button.addEventListener('click', function() {
        let commentId = this.getAttribute('id');
        let replyForm = document.querySelector(`.reply-form-${commentId}`);

        if (replyForm.style.display === 'none' || replyForm.style.display === '') {
            replyForm.style.display = 'block';
        } else {
            replyForm.style.display = 'none';
        }

        console.log('클릭된 commentId:', commentId);
        console.log('관련 답글 폼:', replyForm);

    });
});

$(document).on('click', '.replyWriteBtn', function() {
    let comment_id = $(this).attr('id').split('-')[1];
    let replyContent = $(`#reply-content-${comment_id}`).val();
    let boards_id = $(`#reply-boards_id-${comment_id}`).val();
    let members_id = $(`#reply-members_id-${comment_id}`).val();
    let reparent = comment_id
    let replyForm = $(`.reply-form-${comment_id}`);

    if (replyContent) {
        $.ajax({
            //url: '/boards/writeReply',
            url: '/boards/writeComment',
            type: 'POST',
            data: JSON.stringify({
                reparent: reparent,
                content: replyContent,
                boards_id: boards_id,
                members_id: members_id
            }),
            dataType:"json",
            contentType: 'application/json; charset=utf-8',
            success: function(response) {
                let id = $("#boards_id").val();
                window.location.href = '/boards/detail/' + id;
            },
            error: function() {
                alert("답글 작성에 실패했습니다.");
            }
        });
    }
});

document.querySelectorAll('.updateBtn').forEach(function(button) {
    button.addEventListener('click', function() {
        let comment_id = $(this).attr('id').split('-')[1];
        let commentContent = document.getElementById('comment-content-' + comment_id);
        let updateForm = document.getElementById('update-form-' + comment_id);

        commentContent.style.display = 'none';
        updateForm.style.display = 'block';
    });
});

document.querySelectorAll('.cancel-update-btn').forEach(function(button) {
    button.addEventListener('click', function() {
        let comment_id = $(this).attr('id').split('-')[3];
        let commentContent = document.getElementById('comment-content-' + comment_id);
        let updateForm = document.getElementById('update-form-' + comment_id);

        commentContent.style.display = 'block';
        updateForm.style.display = 'none';
    });
});

document.querySelectorAll('.save-update-btn').forEach(function(button) {
    button.addEventListener('click', function() {
        let comment_id = $(this).attr('id').split('-')[3];
        let updatedContent = $('#update-content-' + comment_id).val();

        console.log(comment_id);
        console.log({
            content: updatedContent,
            updated_at: new Date().toISOString()
        });

        $.ajax({
            url: '/boards/updateComments/' + comment_id,
            type: 'POST',
            data: JSON.stringify({
                content: updatedContent,
                updated_at: new Date().toISOString()
            }),
            dataType: "json",
            contentType: 'application/json; charset=utf-8',
            success: function(response) {
                if (response.code === 1) {
                    let id = $("#boards_id").val();
                    window.location.href = '/boards/detail/' + id;
                } else {
                    alert("댓글 수정에 실패했습니다.");
                }
            },
            error: function() {
                alert("댓글 수정 중 오류가 발생했습니다.");
            }
        });
    });
});

document.querySelectorAll('.deleteBtn').forEach(function(button) {
    button.addEventListener('click', function() {
        let comment_id = $(this).attr('id').split('-')[1]; // comment_id 추출
        let data = { del_yn: "Y" }; // del_yn 값을 "Y"로 설정

        $.ajax({
            url: '/boards/deleteComments/' + comment_id,  // 댓글/대댓글 삭제 요청
            type: "POST",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: 'application/json; charset=utf-8',
            success: function(res) {
                if (res.code == 1) {
                    alert("삭제 완료");
                    // 삭제 후 새로 고침 또는 특정 부분만 갱신하는 방식
                    window.location.reload();  // 전체 페이지 새로고침
                    // 또는 특정 댓글 삭제 후 업데이트 할 경우:
                    // $(this).closest('.comment').remove();  // 삭제된 댓글 DOM을 제거
                } else {
                    alert("삭제에 실패했습니다.");
                }
            },
            error: function() {
                alert("서버와의 연결에 실패했습니다.");
            }
        });
    });
});