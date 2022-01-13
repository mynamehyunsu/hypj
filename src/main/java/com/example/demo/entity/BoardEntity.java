package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="board_tbl")
@Builder
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;
    //FK키설정
    //DB는 오브젝트저장할수없기때문에
    //알아서 MemberEntity와 매칭을 해서 userid 데이터타입과 맞춰서 넣어줌

    //하나의 userid는 여러개의 게시물을 쓸수있따
    //하나의 아이디만 있으니까 기본으로 들고오겠다
    //fetch = FetchType.EAGER 은 안적어줘도 기본적으로 기본으로 들고오도록 되어있다
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userid")
    private MemberEntity memberentity;
    @Column//제목
    private String subject;
    @CreationTimestamp//날짜 자동입력
    private Timestamp createDate;
    @Lob//대용량데이터 저장
    private String content;//섬머노트 라이브러리<html>태그가 섞여서 디자인됨
    @ColumnDefault("0")//조회수
    private int count;

    @OneToMany(mappedBy = "boardentity",fetch = FetchType.EAGER)
    private List<BoardReplyEntity> reply;
    //fetch = FetchType.LAZY 필요할때 들고오겠다(답글테이블에 펼치기 버튼을 누르면 가져오도록)
    //mappedBy 연관관계의 주인이아니다(난FK가아니다)DB에 칼럼을 만들지마라
    //하나의 게시물은 여러개의 답글을 가지고잇다
    //board를 DB에서 가져올때 정보가 필요해서



}