package com.example.mybatis.service;

import com.example.mybatis.dao.BoardsDao;
import com.example.mybatis.dto.boards.*;
import com.example.mybatis.dto.members.MembersDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardsDao boardsDao;
    private final HttpSession httpSession;

    public Map<String, Object> write(WriteDto writeDto){
        System.out.println("service - write call");
        Map<String, Object> response = new HashMap<>();

        MembersDto loginUser = (MembersDto) httpSession.getAttribute("loginUser");

        if (loginUser == null) {
            response.put("code", 0);
            response.put("message", "게시글 작성 실패");
        }

        boardsDao.insert(writeDto.toEntity(loginUser.getId()));
        response.put("code", 1);
        response.put("message", "게시글 작성 성공");

        return response;
    }

    public List<MainListDto> boardsList() {
        try{
            return boardsDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public DetailDto getDetail(Integer id, Integer loginUserId) {
        return boardsDao.findByBoardsId(id, loginUserId);
    }

    public BoardsDto updateForm(Integer id){
        MembersDto membersDto = (MembersDto) httpSession.getAttribute("loginUser");
        BoardsDto boardsDtoUpdate = boardsDao.findById(id);

        return boardsDtoUpdate;
    }

    public Map<String, Object> update(Integer id, UpdateDto updateDto) {
        System.out.println("service - update call");
        MembersDto membersDto = (MembersDto) httpSession.getAttribute("loginUser");
        Map<String, Object> response = new HashMap<>();
        BoardsDto boardsDtoUpdate = boardsDao.findById(id);

        if (boardsDtoUpdate == null) {
            response.put("code", 0);
            response.put("message", "본인 글 만 수정 가능");
            return response;
        }

        boardsDtoUpdate.update(updateDto);
        boardsDao.update(boardsDtoUpdate);

        response.put("code", 1);
        response.put("data", boardsDtoUpdate);
        return response;
    }
}
