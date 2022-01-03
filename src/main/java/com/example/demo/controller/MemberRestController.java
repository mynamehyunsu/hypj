package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.MemberEntity;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController//데이터 처리 컨트롤러
@RequiredArgsConstructor
public class MemberRestController {

    @Autowired
    private final MemberService service;

    @Autowired
    private final AuthenticationManager authenticationmanager;

    //회원가입시 아이디 중복 확인
    @PostMapping("/joinidcheck.do")
    @ResponseBody//요청받은페이지의 body부분을 통으로 가져온다(body부분을 객체로 받아온다)
    //@Controller 일때는 @ResponseBody를 적어줘야하지만 @RestController 일때는 자동으로 붙는다
    public int joinIdCheck(@RequestParam("userid") String userid){
        int result = service.idCheck(userid);

        return result;
    }

    //회원 가입
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

    //회원수정
    //@Transactional 한번에 처리할때 쓰는 어노테이션
    @PostMapping("/memberUpdateProc.do")
    public ModelAndView memberUpdateProc(MemberDTO dto){
        int result = service.update(dto);

        //변경된 세션등록
        Authentication authentication = authenticationmanager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUserid(),dto.getPwd()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        ModelAndView modelAndView = new ModelAndView("/index");
        if(result == 1) {
            modelAndView.addObject("message", "회원수정 성공");
        }else{
            modelAndView.addObject("message", "회원수정 실패");
        }
        return modelAndView;
    }
    @GetMapping("/memberDelete.do")
    public ModelAndView memberDelete(Authentication authentication){

        UserDetails userdetails = (UserDetails)authentication.getPrincipal();

        System.out.println("세션에서 가져온 유저네임"+userdetails.getUsername());
        int result = service.memberDelete(userdetails.getUsername());
        System.out.println("result 값 : " + result);
        ModelAndView modelAndView = new ModelAndView("/index");
        if(result == 1) {

            SecurityContextHolder.clearContext();
            //SecurityContextHolder.getContext().setAuthentication(null);
            modelAndView.addObject("message", "회원삭제 성공");
        }else{
            modelAndView.addObject("message", "회원삭제 실패");
        }
        return modelAndView;
    }




}
