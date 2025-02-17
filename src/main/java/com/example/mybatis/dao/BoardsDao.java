package com.example.mybatis.dao;

import com.example.mybatis.dto.boards.BoardsDto;
import com.example.mybatis.dto.boards.MainListDto;

import java.util.List;
import java.util.Map;

public interface BoardsDao {
    public void insert(BoardsDto boardsDto);
    public List<MainListDto> findAll();
    public List<BoardsDto> findById();
}
