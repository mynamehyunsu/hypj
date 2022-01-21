package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class MemberController {

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
        System.out.println(userdetails.getUsername());
        MemberEntity member = service.getMember(userdetails.getUsername());


            if(member.getPhone() == null){
                MemberDTO memberDTO = MemberDTO.builder()
                        .username(member.getName())
                        .build();
                model.addAttribute("memberDTO", memberDTO);
            }else if(member.getPhone() !=null
                    && member.getAddr1() != null
                    && member.getAddr2() != null
                    && member.getEmail() != null
                    && member.getZipcode() != null
            ){
                MemberDTO memberDTO = MemberDTO.builder()
                        .username(member.getName())
                        .phone1(member.getPhone().substring(0, 3))
                        .phone2(member.getPhone().substring(3, 7))
                        .phone3(member.getPhone().substring(7, member.getPhone().length()))
                        .build();
                model.addAttribute("memberDTO", memberDTO);
            }

        model.addAttribute("member",member);
        return "/view/member/modify/memberUpdate.html";
    }

    @GetMapping("/memberDelete.do")
    public String memberDelete2(Authentication authentication){

        UserDetails userdetails = (UserDetails)authentication.getPrincipal();

        System.out.println("세션에서 가져온 유저네임"+userdetails.getUsername());
        int result = service.memberDelete(userdetails.getUsername());
        System.out.println("result 값 : " + result);
        SecurityContextHolder.clearContext();

//        model.addAttribute("message1","회원삭제 성공");
        return "redirect:/index";
    }

    //가입
    @PostMapping("/join.do")
    public String join(){

        return "/view/member/join/join.html";
    }

    //서점 소개
    @GetMapping("/introduction.do")
    public String introduction(){
        return "/view/introduction/bookIntro.html";
    }




    //장바구니
    @GetMapping("/cart.do")
    public String cart(){
        return "/view/cart/cart.html";
    }

}
