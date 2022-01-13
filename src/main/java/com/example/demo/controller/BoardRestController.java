package com.example.demo.controller;


import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.BoardRepository;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class BoardRestController {

    @Autowired
    private final BoardService boardservice;

    @PostMapping("/boardPostProc.do")
    public ModelAndView boardPostProc(Authentication authentication, BoardDTO dto, @AuthenticationPrincipal MemberEntity memberentity){

        UserDetails userdetails =(UserDetails) authentication.getPrincipal();

       BoardEntity board = boardservice.boardsave(dto,memberentity);//2개 넘겨줘야댐 유저정보(아이디)


        ModelAndView modelAndView = new ModelAndView("/index");
        if(board != null) {
            modelAndView.addObject("message", "글쓰기 성공");
        }else{
            modelAndView.addObject("message", "글쓰기 실패");
        }
        return modelAndView;

    }
}
