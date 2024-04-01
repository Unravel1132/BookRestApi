package com.booksstore.bookstore_1.Controller;

import com.booksstore.bookstore_1.Entity.Book;
import com.booksstore.bookstore_1.Service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "MainMethods")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(
            summary = "Получает список всех книг",
            description = "Получение идет в формате Json"
    )
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @Operation(
            summary = "Получает книгу по ее уникальному индификатору"
    )
    @GetMapping("/{id}") // Указываем путь с переменной типа Long
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> bookOptional = Optional.ofNullable(bookService.getBookById(id));
        if (bookOptional.isPresent()) {
            return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Добавляет новую книгу в базу данных"
    )
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book newBook = bookService.addBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Обновление книгу через запрос тела"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @Operation(
            summary = "Удаляет книгу",
            description = "Не возращает никакого ответа, потенциально делает редирект"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
