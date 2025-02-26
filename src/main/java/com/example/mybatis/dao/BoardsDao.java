package com.example.mybatis.dao;

import com.example.mybatis.dto.boards.BoardsDto;
import com.example.mybatis.dto.boards.DetailDto;
import com.example.mybatis.dto.boards.MainListDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BoardsDao {
    public void insert(BoardsDto boardsDto);
    public List<MainListDto> findAll();
    public BoardsDto findById(Integer id);
    public DetailDto findByBoardsId(@Param("boardsId") Integer boardsId, @Param("loginUserId") Integer loginUserId);
    public void update(BoardsDto boardsDto);
    public void delete(Integer id);
    public void viewCnt(Integer id);
}
