package com.example.demo.dto;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {

    private String userid;
    private String pwd;
    private String username;
    private String phone1;
    private String phone2;
    private String phone3;
    private String email;
    private String zipcode;
    private String addr1;
    private String addr2;
    private String auth;

}
