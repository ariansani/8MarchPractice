package com.example.MarchPractice.service;

import com.example.MarchPractice.model.Book;
import com.example.MarchPractice.repo.BookRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class LibraryService implements BookRepo{
    
    private Logger logger = Logger.getLogger(LibraryService.class.getName());
    private static final String BOOK_ENTITY = "booklist";

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public void save(final Book book){
        redisTemplate.opsForList().leftPush(BOOK_ENTITY, book.getId());
        redisTemplate.opsForHash().put(BOOK_ENTITY+"_Map", book.getId(),book);
    }

    @Override
    public Book findById(final String bookId){
        Book result = (Book)redisTemplate.opsForHash().get(BOOK_ENTITY+"_Map", bookId);
        return result;
    }
    
    @Override
    public List<Book>findAll(int startIndex){
        List<Object> fromBookList = redisTemplate.opsForList().range(BOOK_ENTITY,startIndex,startIndex+9);
        List<Book> books = (List<Book>)redisTemplate.opsForHash()
        .multiGet(BOOK_ENTITY+"_Map", fromBookList)
        .stream()
        .filter(Book.class::isInstance)
        .map(Book.class::cast)
        .toList();

        return books;
    }

}
