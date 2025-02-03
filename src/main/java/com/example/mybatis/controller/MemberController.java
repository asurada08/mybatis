package com.example.mybatis.controller;


import com.example.mybatis.dto.JoinDto;
import com.example.mybatis.dao.MembersDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class MemberController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login";
    }

    @PostMapping("/join")
    public String join(JoinDto joinDto){
        MembersDao.insert(joinDto);
        return "redirect:/login";
    }
}
