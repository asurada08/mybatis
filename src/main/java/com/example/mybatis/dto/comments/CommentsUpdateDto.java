package com.example.mybatis.dto.comments;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentsUpdateDto {
    private String content;
    private String updated_at;
}
