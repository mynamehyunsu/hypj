package com.example.demo.repository;

import com.example.demo.entity.BoardReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardReplyRepository extends JpaRepository<BoardReplyEntity,Integer> {

}
