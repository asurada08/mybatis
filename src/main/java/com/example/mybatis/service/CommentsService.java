package com.example.mybatis.service;

import com.example.mybatis.dao.CommentsDao;
import com.example.mybatis.dto.comments.CommentsDto;
import com.example.mybatis.dto.comments.CommentsListDto;
import com.example.mybatis.dto.comments.CommentsUpdateDto;
import com.example.mybatis.dto.comments.CommentsWriteDto;
import com.example.mybatis.dto.members.MembersDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CommentsService {

    private final CommentsDao commentsDao;
    private final HttpSession httpSession;

    public List<CommentsListDto> getComments(Integer boards_id){
        return commentsDao.findByBoardsId(boards_id);
    }

    public Map<String, Object> writeComments(CommentsWriteDto commentsWriteDto){
        Map<String, Object> response = new HashMap<>();

        try{
            Integer loginUser = (Integer) httpSession.getAttribute("loginUser");

            if (loginUser == null) {
                response.put("code", 0);
                response.put("message", "로그인 후 작성 가능");
                return response;
            }

            commentsDao.insert(commentsWriteDto.toEntity(loginUser));

            boolean isReply = commentsWriteDto.getReparent() != null && commentsWriteDto.getReparent() != 0;

            response.put("code", 1);
            response.put("message", isReply ? "답글 작성 성공" : "댓글 작성 성공");
        } catch (Exception e) {
            e.printStackTrace();
            response.put("code", 0);
            response.put("message", "댓글 작성 중 오류 발생");
        }

        return response;
    }

//    public Map<String, Object> writeComments(CommentsWriteDto commentsWriteDto){
//        Map<String, Object> response = new HashMap<>();
//
//        try{
//            Integer loginUser = (Integer) httpSession.getAttribute("loginUser");
//
//            if (loginUser == null) {
//                response.put("code", 0);
//                response.put("message", "댓글 작성 실패");
//                return response;
//            }
//
//            commentsDao.insert(commentsWriteDto.toEntity(loginUser));
//            response.put("code", 1);
//            response.put("message", "댓글 작성 성공");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.put("code", 0);
//            response.put("message", "댓글 작성 중 오류 발생");
//        }
//
//        return response;
//    }
//
//    public Map<String, Object> writeReply(CommentsWriteDto commentsWriteDto){
//        Map<String, Object> response = new HashMap<>();
//
//        try{
//            Integer loginUser = (Integer) httpSession.getAttribute("loginUser");
//
//            if (loginUser == null) {
//                response.put("code", 0);
//                response.put("message", "답글 작성 실패");
//                return response;
//            }
//
//            commentsDao.insert(commentsWriteDto.toEntity(loginUser));
//            response.put("code", 1);
//            response.put("message", "답글 작성 성공");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.put("code", 0);
//            response.put("message", "답글 작성 중 오류 발생");
//        }
//
//        return response;
//    }

    public Map<String, Object> updateComments(Integer id, CommentsUpdateDto commentsUpdateDto) {
        Map<String, Object> response = new HashMap<>();

        try {
            Integer loginUser = (Integer) httpSession.getAttribute("loginUser");
            CommentsDto commentsDtoUpdate = commentsDao.findById(id);

            if (commentsDtoUpdate == null) {
                response.put("code", 0);
                response.put("message", "본인 글 만 수정 가능");
                return response;
            }

            commentsDtoUpdate.update(commentsUpdateDto);
            commentsDao.update(commentsDtoUpdate);

            response.put("code", 1);
            response.put("data", commentsDtoUpdate);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("code", 0);
            response.put("message", "게시글 수정 중 오류 발생");
        }
        return response;
    }

    public Map<String, Object> deleteComments(Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Integer loginUser = (Integer) httpSession.getAttribute("loginUser");
            CommentsDto commentsDtoDel = commentsDao.findById(id);

            if (commentsDtoDel == null) {
                response.put("code", 0);
                response.put("message", "게시글이 존재 하지 않음");
                return response;
            }

            commentsDao.delete(id);

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
