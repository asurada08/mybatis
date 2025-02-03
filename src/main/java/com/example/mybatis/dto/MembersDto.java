package com.example.mybatis.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MembersDto {
    private Integer id;
    private String login_id;
    private String password;
    private String nickname;
    private String del_yn;
    private String created_at;
    private String updated_at;
}
