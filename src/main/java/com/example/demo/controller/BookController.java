package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
    @GetMapping("/main")
    public String main(){
        return"/view/main.html";
    }

    @GetMapping("/index")
    public String Index(){
        return"/index.html";
    }

    @GetMapping("/login.do")
    public String login(){
        return "/view/member/login/login.html";
    }

    @GetMapping("/agreeJoin.do")
    public String agreeJoin(){
        return "/view/member/join/agreeJoin.html";
    }

    @GetMapping("/join.do")
    public String join(){
        return "/view/member/join/join.html";
    }

    @GetMapping("/memberFind.do")
    public String memberFind(){
        return "/view/member/login/memberFind.html";
    }

    @GetMapping("/mainBookList.do")
    public String mainBookList(){
        return "/view/book/mainBookList.html";
    }
}
