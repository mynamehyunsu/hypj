package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private final MemberService service;

    @Autowired
    private final MemberRepository memberrepo;


    //메인페이지
    @GetMapping("/main")
    public String main(){
        return"/view/main.html";
    }

    @GetMapping("/admin.do")
    public String adminMain(){
        return "/view/adminMain.html";
    }

    @GetMapping("/index")
    public String Index(){
        return"/index.html";
    }

    //로그인
    @GetMapping("/login.do")
    public String login(){
        return "/view/member/login/login.html";
    }


    @GetMapping("/agreeJoin.do")
    public String agreeJoin(){
        return "/view/member/join/agreeJoin.html";
    }
    @GetMapping("/memberFind.do")
    public String memberFind(){
        return "/view/member/login/memberFind.html";
    }


    //가입
    @PostMapping("/join.do")
    public String join(){
        return "/view/member/join/join.html";
    }

    @PostMapping("/joinProc.do")
    public String joinProc(MemberDTO dto){
        System.out.println(dto.toString());
        service.save(dto);
        return "redirect:/index";
    }

    @PostMapping("/joinidcheck.do")
    @ResponseBody
    public int joinIdCheck(@RequestParam("userid") String userid){
        int result = service.idCheck(userid);

        return result;
    }


    //서점 소개
    @GetMapping("/introduction.do")
    public String introduction(){
        return "/view/introduction/bookIntro.html";
    }

    //책소개
    @GetMapping("/mainBookList.do")
    public String mainBookList(){
        return "/view/book/mainBookList.html";
    }

    @GetMapping("/bookRead.do")
    public String bookRead(){
        return "/view/book/bookRead.html";
    }

    //게시판
    @GetMapping("/boardList.do")
    public String boardList(){
        return "/view/board/boardList.html";
    }
    @GetMapping("/boardRead.do")
    public String boardRead(){
        return "/view/board/boardRead.html";
    }
    //장바구니
    @GetMapping("/cart.do")
    public String cart(){
        return "/view/cart/cart.html";
    }

}
