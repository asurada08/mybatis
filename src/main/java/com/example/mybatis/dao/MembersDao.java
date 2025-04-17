package com.example.mybatis.dao;

import com.example.mybatis.dto.members.LoginDto;
import com.example.mybatis.dto.members.MembersDto;
import com.example.mybatis.dto.response.members.LoginRespDto;

import java.util.Map;

public interface MembersDao {
    public int insert(MembersDto membersDto);
    public LoginRespDto login(LoginDto loginDto);
    public int idCheck(MembersDto membersDto);
    public MembersDto findById(Integer id);
    public MembersDto findByIdAndCheck(Map<String, Object> params);
    public void update(MembersDto membersDto);
    public void delete(Integer id);
}
