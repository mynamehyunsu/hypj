package com.example.demo.controller;


import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.BoardReplyDTO;
import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.BoardRepository;
import com.example.demo.service.BoardReplyService;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Fetch;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class BoardRestController {

    @Autowired
    private final BoardService boardservice;

    @Autowired
    private final BoardReplyService boardreplyservice;

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

//    @PostMapping("/boardReplySave.do")
//    public void boardReplySave(BoardReplyDTO dto, @AuthenticationPrincipal MemberEntity memberentity, HttpServletRequest req){
//        HttpSession session = req.getSession();
//        System.out.println("board session 값 : " + session.getAttribute("board"));
//        System.out.println("memberentity 값 : " + memberentity);
//        System.out.println("dto content값 : " + dto.getContent());
//        System.out.println("dto num 값 : " + dto.getNum());
//        BoardEntity board = (BoardEntity) session.getAttribute("board");
//        boardreplyservice.boardReplySave(dto,board,memberentity);
//    }
    @PostMapping("/boardReplySave.do")
    public void boardReplySave(BoardReplyDTO dto, @AuthenticationPrincipal MemberEntity memberentity){


        System.out.println("memberentity 값 : " + memberentity);
        System.out.println("dto content값 : " + dto.getContent());
        System.out.println("dto num 값 : " + dto.getNum());

        boardreplyservice.boardReplySave(dto,memberentity);
    }

}
