package com.example.demo.service;


import com.example.demo.dto.BookDTO;
import com.example.demo.entity.BookEntity;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//스프링이 컴포넌트 스캔을 통해서 Bean등록을 해줌 IoC를 해준다
@RequiredArgsConstructor
public class BookService {

    @Autowired
    private final BookRepository bookrepo;

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


}
