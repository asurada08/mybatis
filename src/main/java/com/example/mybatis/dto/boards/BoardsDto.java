package com.example.mybatis.dto.boards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardsDto {
    private Integer id;
    private String title;
    private String content;
    private Integer members_id;
    private String created_at;
    private String updated_at;
    private Integer view_cnt;
    private String del_yn;
    private Integer category_id;

    public BoardsDto(Integer members_id, String title, String content, Integer category_id){
        this.members_id = members_id;
        this.title = title;
        this.content = content;
        this.category_id = category_id;
    }

    public void update(UpdateDto updateDto) {
        this.title = updateDto.getTitle();
        this.content = updateDto.getContent();
        this.category_id = updateDto.getCategory_id();
    }
}
