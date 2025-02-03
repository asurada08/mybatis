package com.example.mybatis.dao;

import com.example.mybatis.dto.JoinDto;
import com.example.mybatis.dto.LoginDto;
import com.example.mybatis.dto.MembersDto;

import java.util.List;

public interface MembersDao {
    public void insert(JoinDto joinDto);
    public MembersDto findById(Integer id);
    public List<MembersDto> findAll();
    public void update(MembersDto membersDto);
    public void delete(Integer Id, Integer id);
    public MembersDto login(LoginDto loginDto);
}
