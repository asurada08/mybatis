package com.example.mybatis.dto.boards;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MainListDto {
    private Integer id;
    private String title;
    private String nickname;
    private String created_at;
    private Integer view_cnt;
}
