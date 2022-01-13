package com.example.demo.controller;


import com.example.demo.dto.MemberDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardRestController {

    @PostMapping("/boardPostProc.do")
    public String boardPostProc(Authentication authentication){

        UserDetails userdetails =(UserDetails) authentication.getPrincipal();


        return "글쓰기 성공";
    }
}
