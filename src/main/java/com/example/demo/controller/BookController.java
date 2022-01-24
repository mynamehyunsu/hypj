package com.example.demo.controller;


import com.example.demo.dto.BookDTO;
import com.example.demo.dto.PageDTO;
import com.example.demo.entity.BookEntity;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;


@Controller
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private final BookService bookservice;

    //책소개
    @GetMapping("/mainBookList.do")
    public String mainBookList(PageDTO dto, Model model){

        //기본페이지
        int nowPage = 1;
//        System.out.println("booklist에서 nowPage : "+ dto.getNowPage());
        if(dto.getNowPage() != 0){
            nowPage =dto.getNowPage();
        }

        Page<BookEntity> list = bookservice.getBookList(nowPage-1,12);

        //불럭계산(페이징)
        int pagePerBlock =10;
        Long totalRecord = list.getTotalElements(); //전체 게시물(레코드)수
        System.out.println("totalRecord : " + totalRecord);
        int totalPage = list.getTotalPages();//전체 페이지수 size 10으로해서 10개의 개시물로 나눴을때 나오는 페이지수
        System.out.println("totalPage : " + totalPage);
        int nowBlock = (int)Math.ceil((double) nowPage/pagePerBlock); //현재 블럭번호
        System.out.println("nowBlock : " + nowBlock);
        int totalBlock = (int)Math.ceil((double)totalPage/pagePerBlock); //전체 블럭수
        System.out.println("totalBlock : " + totalBlock);

        //블럭에 표시할 StartNum,EndNum 계산(한페이지당 표시할블럭수(15개)에 1부터15까지 계산)
        //만약 2블럭이면 1블럭은 1-15페이지고 2블럭은 16페이지부터 31페이지까지를 계산해야댐
        //해당블럭의 시작페이지와 끝페이지를 계산하는것이기때문에 해당블럭을 계산
        int pageStart =(nowBlock-1)*pagePerBlock+1;//1블럭은1 2블럭은 16 3블럭은 32
        // System.out.println("pageStart : " + pageStart);
        int pageEnd =(pageStart+pagePerBlock)<=totalPage?(pageStart+pagePerBlock):totalPage+1;
        //System.out.println("pageEnd : " + pageEnd);

        model.addAttribute("list",list);
        model.addAttribute("nowBlock",nowBlock);
        model.addAttribute("pagePerBlock",pagePerBlock);
        model.addAttribute("PS",pageStart);
        model.addAttribute("PE",pageEnd);
        model.addAttribute("totalBlock",totalBlock);
        model.addAttribute("nowPage",nowPage);
        System.out.println(list.get());
        System.out.println(list.getContent());
        System.out.println(list.getNumber());
        System.out.println(list.getNumberOfElements());

        return "/view/book/mainBookList.html";
    }

    @GetMapping("/bookRead.do")
    public String bookRead(){



        return "/view/book/bookRead.html";
    }

    @GetMapping("/bookPost.do")
    public String bookPost(){
        return "/view/book/bookPost.html";
    }






}
