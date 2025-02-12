package com.example.mybatis.controller;

import com.example.mybatis.dto.JoinDto;
import com.example.mybatis.dto.LoginDto;
import com.example.mybatis.dto.MembersDto;
import com.example.mybatis.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "member/loginForm";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody LoginDto loginDto, HttpSession httpSession){
        System.out.println("controller - login call");
        System.out.println(loginDto.getLogin_id());

        Map<String, Object> response = memberService.login(loginDto);

        if (response != null && response.containsKey("status") && response.get("status").equals("success")){
            //httpSession.setAttribute("login_id", response.get("login_id"));
            MembersDto loginUser = (MembersDto) response.get("loginUser");
            httpSession.setAttribute("loginUser", loginUser);
        }

        System.out.println(response);
        return response;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
           session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "member/joinForm";
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
        System.out.println("controller - idCheck call");
        System.out.println(login_id);
        MembersDto membersDto = new MembersDto();
        membersDto.setLogin_id(login_id);
        int result = memberService.idCheck(membersDto);
        return result;
    }

    @GetMapping("/updateForm")
    public String updateForm(){
        return "member/updateForm";
    }

}