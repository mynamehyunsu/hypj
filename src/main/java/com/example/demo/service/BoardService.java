package com.example.demo.service;

import com.example.demo.entity.BoardEntity;
import com.example.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
}
