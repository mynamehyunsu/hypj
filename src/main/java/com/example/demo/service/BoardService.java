package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    private final BoardRepository boardrepo;



    public Page<BoardEntity> getBoardList(int page,int size){

        //num를 내림차순으로 받는객체
        Sort sort = Sort.by("num").descending();

        //페이지당 게시물수
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<BoardEntity> list = boardrepo.findAll(pageable);

        return list;
    }

    public BoardEntity boardsave(BoardDTO dto,MemberEntity memberentity){
        LocalDate now = LocalDate.now();
        System.out.println("boardService에 boardsave함수호출했음");
        BoardEntity boardentity = BoardEntity.builder()
                .subject(dto.getSubject())
                .content(dto.getContent())
                //.createDate(Timestamp.valueOf(LocalDateTime.now()))
                .count(0)
                .memberentity(memberentity)
                .build();
       BoardEntity board = boardrepo.save(boardentity);

        return board;
    }
}
