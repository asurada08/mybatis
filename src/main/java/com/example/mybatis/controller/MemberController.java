package com.example.mybatis.controller;

import com.example.mybatis.dto.JoinDto;
import com.example.mybatis.dto.MembersDto;
import com.example.mybatis.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }

    @PostMapping("/join")
    @ResponseBody
    public Map<String, Object> join(@RequestBody JoinDto joinDto){

        System.out.println("controller - join call");
        System.out.println(joinDto.getLogin_id());
        Map<String, Object> response = memberService.join(joinDto);

        System.out.println(response);
        return response;
    }

    @GetMapping("/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("login_id") String login_id) throws Exception{
        MembersDto membersDto = new MembersDto();
        membersDto.setLogin_id(login_id);
        int result = memberService.idCheck(membersDto);
        return result;
    }


}