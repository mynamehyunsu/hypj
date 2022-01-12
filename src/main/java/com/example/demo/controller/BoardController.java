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

        Page<BoardEntity> list = boardService.getBoardList(4,10);

        int nowPage = 3;//기본현재페이지
        //블럭계산
        int pagePerBlock = 15; //한페이지당 표시할블럭수
        Long totalRecord = list.getTotalElements(); //전체 게시물(레코드)수
        int totalPage =list.getTotalPages(); //전체 페이지수 size 10으로해서 10개의 개시물로 나눴을때 나오는 페이지수
        int nowBlock = (int)Math.ceil((double) nowPage/pagePerBlock); //현재 블럭번호
        int totalBlock = (int)Math.ceil((double)totalPage/pagePerBlock); //전체 블럭수

        //블럭에 표시할 StartNum,EndNum 계산(한페이지당 표시할블럭수(15개)에 1부터15까지 계산)
        //만약 2블럭이면 1블럭은 1-15페이지고 2블럭은 16페이지부터 31페이지까지를 계산해야댐
        int pageStart =(nowPage-1)*pagePerBlock+1;//1블럭은1 2블럭은 16 3블럭은 32
        int pageEnd = 1;//진행중


        model.addAttribute("list",list);

        return "/view/board/boardList.html";
    }


    @GetMapping("/boardRead.do")
    public String boardRead(){
        return "/view/board/boardRead.html";
    }
}
