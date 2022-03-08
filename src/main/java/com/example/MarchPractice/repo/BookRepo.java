package com.example.MarchPractice.repo;

import java.util.List;

import com.example.MarchPractice.model.Book;

import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo {
    public void save(final Book book);
    public Book findById(final String bookId);
    public List<Book> findAll(int startIndex);
}
