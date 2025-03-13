package com.example.mybatis.dao;

import com.example.mybatis.dto.boards.BoardsDto;
import com.example.mybatis.dto.comments.CommentsListDto;
import com.example.mybatis.dto.comments.CommentsDto;

import java.util.List;

public interface CommentsDao {
    public List<CommentsListDto> findByBoardsId(Integer boards_id);
    public CommentsDto findById(Integer id);
    public void insert(CommentsDto commentsDto);
    public void update(CommentsDto commentsDto);
    public void delete(Integer id);
}
