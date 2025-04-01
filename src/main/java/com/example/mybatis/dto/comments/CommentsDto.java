package com.example.mybatis.dto.comments;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentsDto {
    private Integer id;
    private String content;
    private Integer members_id;
    private Integer boards_id;
    private String created_at;
    private String updated_at;
    private String del_yn;
    private Integer reparent;

    public CommentsDto(String content, Integer members_id, Integer boards_id, Integer reparent){
        this.content = content;
        this.members_id = members_id;
        this.boards_id = boards_id;
        this.reparent = reparent;
    }

    public void update(CommentsUpdateDto commentsUpdateDto) {
        this.content = commentsUpdateDto.getContent();
        this.updated_at = commentsUpdateDto.getUpdated_at();
    }
}
