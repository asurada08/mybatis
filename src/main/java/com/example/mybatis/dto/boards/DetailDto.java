package com.example.mybatis.dto.boards;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
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
