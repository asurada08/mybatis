package com.example.mybatis.dto.boards;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateDto {
    private String title;
    private String content;
    private Integer category_id;
}
