package com.example.mybatis.dto;

import lombok.Getter;

@Getter
public class BoardsDto {
    private Integer id;
    private String title;
    private String content;
    private String member_id;
    private String created_at;
}
