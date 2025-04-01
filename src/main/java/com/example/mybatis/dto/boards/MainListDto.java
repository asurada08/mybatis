package com.example.mybatis.dto.boards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainListDto {
    private Integer id;
    private String title;
    private String nickname;
    private String created_at;
    private Integer view_cnt;
}
