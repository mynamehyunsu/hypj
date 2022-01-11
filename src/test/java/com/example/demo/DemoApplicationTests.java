package com.example.demo;

import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
class DemoApplicationTests {

//	@Autowired
//	private BoardRepository boardrepo;
//
//	@Test
// 	void insertBoard(){
//
//		for(int i=1; i<=200; i++){
//
//			BoardEntity board = BoardEntity.builder()
//					.content("내용"+i)
//					.subject("제목"+i)
//					.count(0)
//					.createDate(Timestamp.valueOf(LocalDateTime.now()))
//					.build();
//			boardrepo.save(board);
//		}
//	}
//
//	@Autowired
//	private MemberRepository memberrepo;

//	@Test
//	void insertMember(){
//		for(int i=1;i<=100; i++){
//			MemberEntity member = MemberEntity.builder()
//					.userid("아이디"+i)
//					.username("이름"+i)
//					.addr1("첫주소"+i)
//					.addr2("상세주소"+i)
//					.zipcode("우편번호"+i)
//					.phone("0101111222"+Integer.toString(i))
//					.email("aaa"+i+"naver.com")
//					.pwd("1234")
//					.auth("ROLE_USER")
//					.build();
//			memberrepo.save(member);
//		}
//	}






	@Test
	void contextLoads() {
	}

}
