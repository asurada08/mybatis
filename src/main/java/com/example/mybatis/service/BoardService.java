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

        try {
            MembersDto loginUser = (MembersDto) httpSession.getAttribute("loginUser");

            if (loginUser == null) {
                response.put("code", 0);
                response.put("message", "게시글 작성 실패");
                return response;
            }

            boardsDao.insert(writeDto.toEntity(loginUser.getId()));
            response.put("code", 1);
            response.put("message", "게시글 작성 성공");

        } catch (Exception e) {
            e.printStackTrace();
            response.put("code", 0);
            response.put("message", "게시글 작성 중 오류 발생");
        }

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
        try {
            return boardsDao.findByBoardsId(id, loginUserId);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public BoardsDto updateForm(Integer id){
        try {
            MembersDto membersDto = (MembersDto) httpSession.getAttribute("loginUser");

            if (membersDto == null) {
                throw new RuntimeException("로그인 정보 없음");
            }

            BoardsDto boardsDtoUpdate = boardsDao.findById(id);
            return boardsDtoUpdate;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, Object> update(Integer id, UpdateDto updateDto) {
        System.out.println("B service - update call");
        Map<String, Object> response = new HashMap<>();

        try {
            MembersDto membersDto = (MembersDto) httpSession.getAttribute("loginUser");
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

        } catch (Exception e) {
            e.printStackTrace();
            response.put("code", 0);
            response.put("message", "게시글 수정 중 오류 발생");
        }
        return response;
    }

    public Map<String, Object> delete(Integer id) {
        System.out.println("B service - delete call");
        Map<String, Object> response = new HashMap<>();

        try {
            BoardsDto boardsDtoDel = boardsDao.findById(id);

            if (boardsDtoDel == null) {
                response.put("code", 0);
                response.put("message", "게시글이 존재 하지 않음");
                return response;
            }

            boardsDao.delete(id);

            response.put("code", 1);
            response.put("data", "게시글 삭제 성공");
        } catch (Exception e) {
            e.printStackTrace();
            response.put("code", 0);
            response.put("message", "게시글 삭제 중 오류 발생");
        }

        return response;
    }
}
