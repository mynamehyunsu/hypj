package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public String Index(HttpServletRequest req){


        System.out.println("메시지 : " + req.getAttribute("message"));
        return "/index.html";

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

    //회원수정
    @GetMapping("/memberUpdate.do")
    public String memberUpdate(Model model, Authentication authentication){
        UserDetails userdetails = (UserDetails) authentication.getPrincipal();
        MemberEntity member = service.getMember(userdetails.getUsername());
        MemberDTO memberDTO = MemberDTO.builder()
                .phone1(member.getPhone().substring(0,3))
                .phone2(member.getPhone().substring(3,7))
                .phone3(member.getPhone().substring(7,member.getPhone().length()))
                .build();
        model.addAttribute("memberDTO",memberDTO);
        model.addAttribute("member",member);
        return "/view/member/modify/memberUpdate.html";
    }

    //가입
    @PostMapping("/join.do")
    public String join(){

        return "/view/member/join/join.html";
    }

    @PostMapping("/joinProc.do")
    public ModelAndView joinProc(MemberDTO dto){
        //System.out.println(dto.toString());
        MemberEntity entity = service.save(dto);


            ModelAndView modelAndView = new ModelAndView("/index");
        if(entity != null) {
            modelAndView.addObject("message", "회원가입 성공");
        }else{
            modelAndView.addObject("message", "회원가입 실패");
        }
        return modelAndView;
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
