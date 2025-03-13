package com.example.mybatis.dto.comments;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentsListDto {
    private Integer id;
    private String content;
    private String nickname;
    private String created_at;
    private Integer reparent;
    private Integer members_id;
    private Integer boards_id;
}
