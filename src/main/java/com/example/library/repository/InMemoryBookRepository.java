package com.example.library.repository;

import com.example.library.model.Book;
import java.util.*;

public class InMemoryBookRepository implements BookRepository {
    private final Map<String, Book> storage = new HashMap<>();

    @Override
    public Book save(Book book) {
        storage.put(book.getId(), book);
        return book;
    }

    @Override
    public Optional<Book> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public boolean deleteById(String id) {
        return storage.remove(id) != null;
    }
}