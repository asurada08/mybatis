package com.example.mybatis.dto.comments;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentsUpdateDto {
    private String content;
    private String updated_at;
}
