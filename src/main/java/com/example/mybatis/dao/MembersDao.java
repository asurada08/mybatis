package com.example.mybatis.dao;

import com.example.mybatis.dto.members.LoginDto;
import com.example.mybatis.dto.members.MembersDto;
import com.example.mybatis.dto.response.members.LoginRespDto;

public interface MembersDao {
    public int insert(MembersDto membersDto);
    public Integer login(String login_id, String password);
    LoginRespDto login2(LoginDto loginDto);
    public String findNicknameById(Integer id);
    public int idCheck(MembersDto membersDto);
    public MembersDto findById(Integer id);
    public void update(MembersDto membersDto);
    public void delete(Integer id);
}
