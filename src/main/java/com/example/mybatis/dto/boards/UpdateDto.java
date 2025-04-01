package com.example.mybatis.dto.boards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDto {
    private String title;
    private String content;
    private Integer category_id;
}
