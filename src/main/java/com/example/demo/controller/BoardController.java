package com.example.demo.controller;


import com.example.demo.entity.BoardEntity;
import com.example.demo.repository.BoardRepository;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private final BoardService boardService;

    //게시판

    //글목록
    @GetMapping("/boardList.do")
    public String boardList(Model model)
    {
        Page<BoardEntity> list = boardService.getBoardList(0,10);
        System.out.println("리스트 가져오기 성공");
        model.addAttribute("list",list);
        return "/view/board/boardList.html";
    }


    @GetMapping("/boardRead.do")
    public String boardRead(){
        return "/view/board/boardRead.html";
    }
}
