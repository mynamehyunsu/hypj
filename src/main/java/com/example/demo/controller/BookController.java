package com.example.demo.controller;


import com.example.demo.dto.BookDTO;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.stereotype.Controller;
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
public class BookController {

    //책소개
    @GetMapping("/mainBookList.do")
    public String mainBookList(){

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
