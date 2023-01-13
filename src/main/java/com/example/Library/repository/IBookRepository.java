package com.example.Library.repository;

import com.example.Library.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, String> {
}