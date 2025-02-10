package com.example.mybatis.dao;

import com.example.mybatis.dto.LoginDto;
import com.example.mybatis.dto.MembersDto;

public interface MembersDao {
    public int insert(MembersDto membersDto);
    public MembersDto findByLogin_id(String login_id);
    public int idCheck(MembersDto membersDto);
}
