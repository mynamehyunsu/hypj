package com.example.demo.service;


import com.example.demo.dto.BookDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.entity.BookEntity;
import com.example.demo.entity.BookReplyEntity;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.BookReplyRepository;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
//스프링이 컴포넌트 스캔을 통해서 Bean등록을 해줌 IoC를 해준다
@RequiredArgsConstructor
public class BookService {

    @Autowired
    private final BookRepository bookrepo;

    @Autowired
    private final BookReplyRepository bookreplyrepo;

    @Transactional
    public int postBook(BookDTO dto){
        BookEntity bookentity = BookEntity.builder()
                .subject(dto.getSubject())
                .content(dto.getContent())
                .price(dto.getPrice())
                .filename(dto.getFilename())
                .sfilename(dto.getSfilename())
                .folderPath(dto.getFolderpath())
                .build();
        try {
            bookrepo.save(bookentity);
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("BookService : postBook() : "+e.getMessage());
        }
        return -1;
    }

    public Page<BookEntity> getBookList(int page,int size){
        //내림차순 정렬
        Sort sort = Sort.by("num").descending();

        //페이지당 게시물수
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<BookEntity> list = bookrepo.findAll(pageable);
        return list;
    }

    public void Upcount(int num){
        Optional<BookEntity> book =bookrepo.findById(num);
        book.get().setCount(book.get().getCount()+1);
        bookrepo.save(book.get());
        System.out.println("bookservice의 upcount함수실행");
    }
    @Transactional
    public BookEntity getBook(int num){
        return bookrepo.findById(num)
                .orElseThrow(()->{return new IllegalArgumentException("글 상세보기 실패 책 번호 못찾습니다");});
    }


    @Transactional
    public void postBookReply(MemberEntity memberentity, int bookNum, BookReplyEntity bookreplyentity) {

        //해당함수종료시 (Service가종료될때)트랜잭션이 종료됩니다. 이때 더티체킹-자동업데이트가됨.db flush
        BookEntity book = bookrepo.findById(bookNum).orElseThrow(()->{
            return new IllegalArgumentException("댓글쓰기 실패 : 해당게시글(num)을 찾을수없읍니다");
        });//영속화 완료

        bookreplyentity.setMemberentity(memberentity);
        bookreplyentity.setBookentity(book);
        bookreplyrepo.save(bookreplyentity);
    }

    @Transactional
    public void replyDelete(int reply_Num){
        System.out.println("replyDelete 함수에서 받은 replyNum : " + reply_Num);
        bookreplyrepo.deleteById(reply_Num);
    }
}
