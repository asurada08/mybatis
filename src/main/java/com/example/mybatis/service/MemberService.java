package com.example.mybatis.service;

import com.example.mybatis.dto.LoginDto;
import com.example.mybatis.dto.UpdateDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import com.example.mybatis.dao.MembersDao;
import com.example.mybatis.dto.JoinDto;
import com.example.mybatis.dto.MembersDto;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MembersDao membersDao;

    public Map<String, Object> join(JoinDto joinDto){

        System.out.println("service - join call");
        MembersDto membersDto = joinDto.toEntity();

        Map<String, Object> response = new HashMap<>();

        String result = "fall";

        int memberId = -1;

        try {
            memberId = membersDao.insert(membersDto);
        } catch (Exception e) {
            System.out.println(e);
        }

        if(memberId == 1) {
            result = "ok";
        }
        response.put("result", result);

        return response;
    }

    public int idCheck(MembersDto membersDto) throws Exception{
        int result = membersDao.idCheck(membersDto);
        return result;
    }

    public Map<String, Object> login(LoginDto loginDto) {
        System.out.println("service - login call");
        Map<String, Object> response = new HashMap<>();

//        if (loginDto.getLogin_id() == null || loginDto.getLogin_id().isBlank()) {
//            response.put("status", "fail");
//            response.put("message", "아이디를 입력해주세요");
//            return response;
//        }
//        if (loginDto.getPassword() == null || loginDto.getPassword().isBlank()) {
//            response.put("status", "fail");
//            response.put("message", "비밀번호를 입력해주세요");
//            return response;
//        }

        MembersDto membersDtoLogin = membersDao.login(loginDto.getLogin_id(), loginDto.getPassword());

//        if (membersDtoLogin == null) {
//            response.put("status", "fail");
//            response.put("message", "아이디를 확인해주세요");
//            return response;
//        }
//
//        if (!(membersDtoLogin.getPassword().equals(loginDto.getPassword()))) {
//            response.put("status", "fail");
//            response.put("message", "비밀번호를 확인해주세요");
//            return response;
//        }
        if (membersDtoLogin == null) {
            response.put("status", "fail");
            response.put("message", "아이디, 비밀번호를 확인해주세요");
            return response;
        }

        response.put("status", "success");
        response.put("loginUser", membersDtoLogin);
        return response;
    }

}
