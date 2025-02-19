package com.example.mybatis.controller;

import com.example.mybatis.dao.BoardsDao;
import com.example.mybatis.dto.boards.BoardsDto;
import com.example.mybatis.dto.boards.DetailDto;
import com.example.mybatis.dto.boards.UpdateDto;
import com.example.mybatis.dto.boards.WriteDto;
import com.example.mybatis.dto.members.MembersDto;
import com.example.mybatis.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class BoardsController {
    private final BoardsDao boardsDao;
    private final BoardService boardService;
    private final HttpSession httpSession;

    @GetMapping({"/", "/home"})
    public String board(HttpSession session, Model model) {
        MembersDto loginUser = (MembersDto) session.getAttribute("loginUser");
        if (loginUser != null) {
            model.addAttribute("loginUser", loginUser);
        }
        System.out.println("controller - list call");
        model.addAttribute("boardsList", boardService.boardsList());
        return "boards/home";
    }

    @GetMapping("/boards/writeForm")
    public String writeForm() {
        MembersDto loginUser = (MembersDto) httpSession.getAttribute("loginUser");
        return "/boards/writeForm";
    }

    @PostMapping("/boards/write")
    @ResponseBody
    public Map<String, Object> write(@RequestBody WriteDto writeDto) {
        System.out.println("controller - write call");
        Map<String, Object> response = boardService.write(writeDto);
        System.out.println(response);
        return response;
    }

    @GetMapping("/boards/detail/{id}")
    public String getDetail(@PathVariable Integer id, Model model){
        MembersDto loginUser = (MembersDto) httpSession.getAttribute("loginUser");
        if(loginUser == null) {
            model.addAttribute("detailDto", boardService.getDetail(id, 0));
        } else {
            model.addAttribute("detailDto", boardService.getDetail(id, loginUser.getId()));
        }
        return "/boards/detail";
    }

    @GetMapping("/boards/updateForm/{id}")
    public String updateForm(@PathVariable Integer id, Model model){
        BoardsDto boardsDtoUpdate = boardService.updateForm(id);
        model.addAttribute("boardsDtoUpdate", boardsDtoUpdate);
        return "/boards/updateForm";
    }

    @PostMapping("/boards/updateB/{id}")
    @ResponseBody
    public Map<String, Object> updateB(@PathVariable Integer id, @RequestBody UpdateDto updateDto){
        System.out.println("B controller - update call id : " + id);
        Map<String, Object> response = boardService.updateB(id, updateDto);
        return response;
    }

    @PostMapping("/deleteB/{id}")
    @ResponseBody
    public Map<String, Object> deleteB(@PathVariable Integer id, HttpServletRequest request){
        System.out.println("B controller - delete call");
        Map<String, Object> response = boardService.deleteB(id);
        return response;
    }
}
