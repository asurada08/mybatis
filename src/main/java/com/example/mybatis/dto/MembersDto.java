package com.example.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MembersDto {
    private Integer id;
    private String login_id;
    private String password;
    private String nickname;
    private String del_yn = "N";
    private String created_at;
    private String updated_at;

    public MembersDto (String login_id, String password, String nickname) {
        this.login_id = login_id;
        this.password = password;
        this.nickname = nickname;
    }
}
