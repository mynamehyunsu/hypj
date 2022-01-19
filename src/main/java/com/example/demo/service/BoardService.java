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
import java.util.Optional;

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

    public BoardEntity getBoard(Long num){
        Optional<BoardEntity> board = boardrepo.findById(num);
        System.out.println("num을 통해 가져온 게시판목록"+board.get());
        System.out.println("board.get().getReply(): "+board.get().getReply());
        return board.get();
    }

    public void Upcount(Long num){
        Optional<BoardEntity> board =boardrepo.findById(num);
        board.get().setCount(board.get().getCount()+1);
        boardrepo.save(board.get());
        System.out.println("upcount함수실행");
    }

    public void delete(Long num) {
        boardrepo.deleteById(num);
    }
}
