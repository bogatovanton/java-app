package com.example.library;

import com.example.library.model.Book;
import com.example.library.repository.InMemoryBookRepository;
import com.example.library.service.LibraryService;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryService service = new LibraryService(new InMemoryBookRepository());
        Scanner scanner = new Scanner(System.in);

        System.out.println("📚 Welcome to Home Library!");

        while (true) {
            System.out.println("\n1. Add book");
            System.out.println("2. Show all books");
            System.out.println("3. Find by author");
            System.out.println("4. Issue book");
            System.out.println("5. Return book");
            System.out.println("6. Delete book");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Author: ");
                        String author = scanner.nextLine();
                        System.out.print("Year: ");
                        int year = Integer.parseInt(scanner.nextLine());
                        Book b = service.addBook(title, author, year);
                        System.out.println("Added: " + b);
                    }
                    case "2" -> service.findAll().forEach(System.out::println);
                    case "3" -> {
                        System.out.print("Author: ");
                        List<Book> found = service.findByAuthor(scanner.nextLine());
                        found.forEach(System.out::println);
                    }
                    case "4" -> {
                        System.out.print("Book ID: ");
                        System.out.println(service.issueBook(scanner.nextLine()) ? "Issued" : "Failed");
                    }
                    case "5" -> {
                        System.out.print("Book ID: ");
                        System.out.println(service.returnBook(scanner.nextLine()) ? "Returned" : "Failed");
                    }
                    case "6" -> {
                        System.out.print("Book ID: ");
                        System.out.println(service.deleteBook(scanner.nextLine()) ? "Deleted" : "Not found");
                    }
                    case "0" -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Unknown option");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
