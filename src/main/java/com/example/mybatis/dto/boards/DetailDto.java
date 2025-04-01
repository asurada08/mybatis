package com.example.mybatis.dto.boards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailDto {
    private Integer id;
    private String title;
    private String content;
    private String nickname;
    private Integer view_cnt;
    private String created_at;
    private String updated_at;
    private Integer members_id;
    private String category_name;
}
