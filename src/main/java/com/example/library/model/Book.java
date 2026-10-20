package com.example.library.model;

import java.util.Objects;

public class Book {
    private final String id;
    private final String title;
    private final String author;
    private final int year;
    private boolean isIssued;

    public Book(String id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isIssued = false;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public boolean isIssued() { return isIssued; }

    public void issue() { this.isIssued = true; }
    public void returnBook() { this.isIssued = false; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("[%s] '%s' by %s (%d) - %s",
                id, title, author, year, isIssued ? "ISSUED" : "available");
    }
}