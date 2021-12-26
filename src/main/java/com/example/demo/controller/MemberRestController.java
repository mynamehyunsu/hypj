package com.example.demo.controller;

import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController//데이터 처리 컨트롤러
@RequiredArgsConstructor
public class MemberRestController {

    @Autowired
    private final MemberService service;

    //아이디 중복 확인
    @PostMapping("/joinidcheck.do")
    @ResponseBody//요청받은페이지의 body부분을 통으로 가져온다(body부분을 객체로 받아온다)
    //@Controller 일때는 @ResponseBody를 적어줘야하지만 @RestController 일때는 자동으로 붙는다
    public int joinIdCheck(@RequestParam("userid") String userid){
        int result = service.idCheck(userid);

        return result;
    }



}
