package com.example.demo.service;

import com.example.demo.dto.BoardReplyDTO;
import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.BoardReplyEntity;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.BoardReplyRepository;
import com.example.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BoardReplyService {

    @Autowired
    private final BoardReplyRepository boardreplyrepo;

    @Autowired
    private final BoardRepository boardrepo;

    public void boardReplySave(BoardReplyDTO dto, MemberEntity memberentity){
        LocalDate now = LocalDate.now();
        System.out.println("boardReplySave함수에서 가지고있는 dto : " + dto.getContent()+dto.getNum());
        Optional<BoardEntity> board = boardrepo.findById((long) dto.getNum());
        BoardReplyEntity reply = BoardReplyEntity.builder()
                .content(dto.getContent())
                //.createDate(Timestamp.valueOf(now.toString()))
                .boardentity(board.get())
                .memberentity(memberentity)
                .build();
        boardreplyrepo.save(reply);
    }
}
