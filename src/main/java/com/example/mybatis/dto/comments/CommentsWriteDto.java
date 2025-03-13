package com.example.mybatis.dto.comments;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentsWriteDto {
    private String content;
    private Integer members_id;
    private String nickname;
    private Integer boards_id;
    private Integer reparent;

    public CommentsDto toEntity(Integer members_id) {
        CommentsDto commentsDto = new CommentsDto(this.content, members_id, this.boards_id, this.reparent);
        return  commentsDto;
    }
}
