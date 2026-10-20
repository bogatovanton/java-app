package com.example.library.repository;

import com.example.library.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(String id);
    List<Book> findAll();
    boolean deleteById(String id);
}