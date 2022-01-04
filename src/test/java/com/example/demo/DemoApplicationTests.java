package com.example.demo;

import com.example.demo.entity.BoardEntity;
import com.example.demo.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class DemoApplicationTests {

//	@Autowired
//	private BoardRepository boardrepo;

//	@Test
// 	void insertBoard(){
//		LocalDate now = LocalDate.now();
//		for(int i=1; i<=200; i++){
//			BoardEntity board = BoardEntity.builder()
//					.userid("userid"+i)
//					.subject("제목"+ i)
//					.content("내용" + i)
//					.regdate(now.toString())
//					.pwd(Integer.toString(i))
//					.count(i)
//					.build();
//			boardrepo.save(board);
//		}
//	}






	@Test
	void contextLoads() {
	}

}
