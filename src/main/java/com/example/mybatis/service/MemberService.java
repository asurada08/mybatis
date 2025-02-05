package com.example.mybatis.service;

import com.example.mybatis.dto.LoginDto;
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

    public MembersDto login(LoginDto loginDto) {
        MembersDto membersDtoPW = membersDao.findByLogin_id(loginDto.getLogin_id());

        if(membersDtoPW == null){
            return null;
        }
        if(!(membersDtoPW.getPassword().equals(loginDto.getPassword()))){
            return null;
        }
        return membersDtoPW;
    }

    //public int idCheck(String login_id) throws Exception{
    //}

}
