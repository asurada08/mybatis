package com.example.mybatis.dto.boards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WriteDto {
    private Integer members_id;
    private String title;
    private String content;
    private Integer category_id;

    public BoardsDto toEntity(Integer members_id) {
        BoardsDto boardsDto = new BoardsDto(members_id, this.title, this.content, this.category_id);
        return boardsDto;
    }
}
