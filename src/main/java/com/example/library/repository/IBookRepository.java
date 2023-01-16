package com.example.library.repository;

import com.example.library.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book, String> {

    @Query("SELECT b FROM Book b WHERE b.title LIKE :value OR " +
            "b.year LIKE :value OR " +
            "b.author LIKE :value OR " +
            "b.editorial LIKE: value ORDER BY b.title ASC")
    List<Book> getByValue(@Param("value")String value);

    @Query("SELECT b FROM Book b WHERE b.deleted = false ORDER BY b.title ASC")
    List<Book> getForEnable();

    @Query("SELECT b FROM Book b WHERE b.deleted = true ORDER BY b.title ASC")
    List<Book> getForDisable();
}