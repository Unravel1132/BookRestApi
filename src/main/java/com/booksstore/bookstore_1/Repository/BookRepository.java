package com.booksstore.bookstore_1.Repository;

import com.booksstore.bookstore_1.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
