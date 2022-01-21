package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="book_Reply_tbl")
public class BookReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    @Column(nullable = false,length = 200)
    private String content;
    @CreationTimestamp
    private Timestamp createDate;

    @ManyToOne
    @JoinColumn(name="userid")
    private MemberEntity memberentity;
    @ManyToOne
    @JoinColumn(name="bookNum")
    private BookEntity bookentity;

}
