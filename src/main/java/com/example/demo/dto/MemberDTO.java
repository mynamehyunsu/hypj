package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MemberDTO {

    private String userid;
    private String pwd;
    private String username;
    private String phone;
    private String email;
    private String zipcode;
    private String addr1;
    private String addr2;
    private String auth;

}
