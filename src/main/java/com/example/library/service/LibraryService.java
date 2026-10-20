package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class LibraryService {
    private final BookRepository repository;

    public LibraryService(BookRepository repository) {
        this.repository = repository;
    }

    public Book addBook(String title, String author, int year) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
        if (year < 0 || year > java.time.Year.now().getValue()) {
            throw new IllegalArgumentException("Invalid year");
        }

        String id = UUID.randomUUID().toString().substring(0, 8);
        Book book = new Book(id, title, author, year);
        return repository.save(book);
    }

    public Optional<Book> findById(String id) {
        return repository.findById(id);
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public List<Book> findByAuthor(String author) {
        return repository.findAll().stream()
                .filter(b -> b.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public boolean issueBook(String id) {
        Optional<Book> book = repository.findById(id);
        if (book.isPresent() && !book.get().isIssued()) {
            book.get().issue();
            return true;
        }
        return false;
    }

    public boolean returnBook(String id) {
        Optional<Book> book = repository.findById(id);
        if (book.isPresent() && book.get().isIssued()) {
            book.get().returnBook();
            return true;
        }
        return false;
    }

    public boolean deleteBook(String id) {
        return repository.deleteById(id);
    }
}