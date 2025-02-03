package com.example.mybatis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardsDto {
    private Integer id;
    private String title;
    private String content;
    private String member_id;
    private String created_at;
}
