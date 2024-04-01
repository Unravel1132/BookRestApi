package com.booksstore.bookstore_1.Service;


import com.booksstore.bookstore_1.Entity.Book;
import com.booksstore.bookstore_1.Repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            throw new NoSuchElementException("Book not found with id: " + id);
        }

    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Book book = getBookById(id);
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setPrice(bookDetails.getPrice());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {

        Book book = getBookById(id);
        if (book != null) {
            logger.info("Удаление книги по индификатору", id);
            bookRepository.delete(book);
        }
        else {
            logger.warn("Ошибка");
        }
        }
    }
