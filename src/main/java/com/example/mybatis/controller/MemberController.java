package com.example.mybatis.controller;

import com.example.mybatis.dto.members.JoinDto;
import com.example.mybatis.dto.members.LoginDto;
import com.example.mybatis.dto.members.MembersDto;
import com.example.mybatis.dto.members.UpdateDto;
import com.example.mybatis.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;
    private final HttpSession httpSession;

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
        return "redirect:/home";
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

    @GetMapping("/updateForm/{id}")
    public String updateForm(@PathVariable Integer id, Model model){
        MembersDto membersDtoUpdate = memberService.updateForm(id);
        model.addAttribute("loginUser", membersDtoUpdate);
        return "member/updateForm";
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public Map<String, Object> update(@PathVariable Integer id, @RequestBody UpdateDto updateDto){
        System.out.println("controller - update call");
        Map<String, Object> response = memberService.update(id, updateDto);

//        if (response.get("code").equals(1)) {
//            MembersDto membersDtoUpdate = (MembersDto) response.get("data");
//            httpSession.setAttribute("loginUser", membersDtoUpdate);
//        }
        return response;
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable Integer id, HttpServletRequest request){
        System.out.println("controller - delete call");
        Map<String, Object> response = memberService.delete(id);

        HttpSession session = request.getSession(false); // 현재 세션을 가져옵니다.
        if (session != null) {
            session.invalidate(); // 세션을 무효화하여 로그아웃 처리
        }

        return response;
    }

}