package com.example.mybatis.service;

import com.example.mybatis.dao.BoardsDao;
import com.example.mybatis.dto.boards.BoardsDto;
import com.example.mybatis.dto.boards.MainListDto;
import com.example.mybatis.dto.boards.WriteDto;
import com.example.mybatis.dto.members.MembersDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
}
