package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.InMemoryBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {

    private LibraryService service;

    @BeforeEach
    void setUp() {
        service = new LibraryService(new InMemoryBookRepository());
    }

    @Test
    void shouldAddBookAndAssignId() {
        Book book = service.addBook("Clean Code", "Robert Martin", 2008);

        assertNotNull(book.getId());
        assertEquals("Clean Code", book.getTitle());
        assertEquals("Robert Martin", book.getAuthor());
        assertEquals(2008, book.getYear());
        assertFalse(book.isIssued());
    }

    @Test
    void shouldNotAddBookWithEmptyTitle() {
        assertThrows(IllegalArgumentException.class,
                () -> service.addBook("", "Author", 2020));
    }

    @Test
    void shouldNotAddBookWithInvalidYear() {
        assertThrows(IllegalArgumentException.class,
                () -> service.addBook("Title", "Author", -5));
    }

    @Test
    void shouldFindBookById() {
        Book book = service.addBook("Title", "Author", 2020);
        assertTrue(service.findById(book.getId()).isPresent());
        assertFalse(service.findById("nonexistent").isPresent());
    }

    @Test
    void shouldFindBooksByAuthor() {
        service.addBook("Book 1", "Tolkien", 1954);
        service.addBook("Book 2", "Tolkien", 1937);
        service.addBook("Book 3", "Rowling", 1997);

        List<Book> tolkienBooks = service.findByAuthor("Tolkien");
        assertEquals(2, tolkienBooks.size());
    }

    @Test
    void shouldIssueAndReturnBook() {
        Book book = service.addBook("Title", "Author", 2020);

        assertTrue(service.issueBook(book.getId()));
        assertTrue(service.findById(book.getId()).get().isIssued());

        assertFalse(service.issueBook(book.getId()));

        assertTrue(service.returnBook(book.getId()));
        assertFalse(service.findById(book.getId()).get().isIssued());

        assertFalse(service.returnBook(book.getId()));
    }

    @Test
    void shouldDeleteBook() {
        Book book = service.addBook("Title", "Author", 2020);
        assertTrue(service.deleteBook(book.getId()));
        assertFalse(service.deleteBook(book.getId()));
        assertEquals(0, service.findAll().size());
    }

    @Test
    void shouldReturnAllBooks() {
        service.addBook("A", "Author", 2000);
        service.addBook("B", "Author", 2001);
        assertEquals(2, service.findAll().size());
    }
}
