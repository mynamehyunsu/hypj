package com.example.demo.entity;


import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="book_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude="bookReply")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;
    @Column
    private String subject;
    @Column
    private String content;
    @Column
    private int price;
    @Column
    private String filename;
    @Column
    private String sfilename;
    @Column
    private String folderPath;
    @ColumnDefault("0")
    private int count;
    @CreationTimestamp
    private Timestamp createDate;
    @OneToMany(mappedBy="bookentity",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE,orphanRemoval = true)
    @OrderBy("num desc")
    private List<BookReplyEntity> bookReply;
    //fetch = FetchType.LAZY 필요할때 들고오겠다(답글테이블에 펼치기 버튼을 누르면 가져오도록)
    //mappedBy 연관관계의 주인이아니다(난FK가아니다)DB에 칼럼을 만들지마라
    //하나의 게시물은 여러개의 답글을 가지고잇다
    //board를 DB에서 가져올때 정보가 필요해서


}
